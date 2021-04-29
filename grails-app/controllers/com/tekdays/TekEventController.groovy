package com.tekdays

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional
import groovy.json.JsonBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TekEventController extends RestfulController {

    // Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(TekEventController.class)

    def taskService
    def datatablesSourceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", revisions: "PUT", apiData: "GET"]

    def index() {
        [properties: [message(code: 'tekEvent.name.label'), message(code: 'tekEvent.city.label'), message(code: 'tekEvent.venue.label'),
                      message(code: 'tekEvent.startDate.label'), message(code: 'tekEvent.endDate.label'),
                      message(code: 'tekEvent.description.label'), message(code: 'tekEvent.organizer.label'),
                      message(code: 'default.button.edit.label'), message(code: "revisions.get.label"), "OrganizerId"]]
    }

    def dataTablesRenderer() {
        def propertiesToRender = ["name", "city", "venue", "startDate", "endDate", "description", "organizer", "id", "id", "organizerId"]
        def entityName = TekEvent.class.simpleName
        render datatablesSourceService.dataTablesSource(propertiesToRender, entityName, params)
    }

    def show(Long id) {
        def tekEventInstance
        if (params.nickname) {
            tekEventInstance = TekEvent.findByNickname(params.nickname)
        } else {
            tekEventInstance = TekEvent.get(id)
        }
        if (!tekEventInstance) {
            if (params.nickname) {
                flash.message = "Event not found with nickname ${params.nickname}"
            } else {
                flash.message = "Event not found with id $id"
            }
            redirect(action: "index")
            return
        }
        [tekEventInstance: tekEventInstance]
    }

    def volunteer = {
        def event = TekEvent.get(params.id)
        event.addToVolunteers(session.user)
        event.save flush: true
        render "Thank you for Volunteering"
    }

    def create() {
        respond new TekEvent(params)
    }

    @Transactional
    def save(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        if (tekEventInstance.hasErrors()) {
            respond tekEventInstance.errors, view: 'create'
            return
        }

        tekEventInstance.save flush: true

        // Logger for event creation
        LOGGER.info("Event with name: ${tekEventInstance?.name}")

        taskService.addDefaultTasks(tekEventInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect tekEventInstance
            }
            '*' { respond tekEventInstance, [status: CREATED] }
        }
    }

    def edit(TekEvent tekEventInstance) {
        // Only organizer of the event can edit the event
        if (!validateOrganizer(tekEventInstance)) {
            redirect controller: 'tekEvent', action: 'show', id: tekEventInstance?.id
        }
        respond tekEventInstance
    }

    @Transactional
    def update(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        if (tekEventInstance.hasErrors()) {
            respond tekEventInstance.errors, view: 'edit'
            return
        }

        tekEventInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect tekEventInstance
            }
            '*' { respond tekEventInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekEvent tekEventInstance) {

        if (tekEventInstance == null) {
            notFound()
            return
        }

        // Only organizer of the event can delete the event
        if (!validateOrganizer(tekEventInstance)) {
            redirect controller: 'tekEvent', action: 'show', id: tekEventInstance?.id
        }

        tekEventInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    // Check if current user is the organizer of the event
    private boolean validateOrganizer(TekEvent tekEventInstance) {
        if (TekUser.get(session.user?.id) == tekEventInstance?.organizer) {
            return true
        }
        return false
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def apiData() {
        def data = TekEvent.get(params.id)
        if (data) {
            def builder = new JsonBuilder()
            def root = builder.event {
                name data.name
                city data.city
                venue data.venue
                startDate data.startDate.format("yyyy-MMM-dd")
                endDate data.endDate.format("yyyy-MMM-dd")
                description data.description
                organizer data.organizer.fullName
                volunteers data.volunteers.collect {
                    [name: it.fullName]
                }
            }
            render root as JSON
        } else {
            data = TekEvent.list()
            def builder = new JsonBuilder()
            def root = builder.events {
                event data.collect {
                    [name       : it.name,
                     city       : it.city,
                     venue      : it.venue,
                     startDate  : it.startDate.format("yyyy-MMM-dd"),
                     endDate    : it.endDate.format("yyyy-MMM-dd"),
                     description: it.description,
                     organizer  : it.organizer.fullName,
                     volunteers : it.volunteers.collect { volunteer ->
                         [name: volunteer.fullName]
                     }]
                }
            }
            render root as JSON
        }
    }
}
