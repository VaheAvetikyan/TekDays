package com.tekdays

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekEventController {

    // Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(TekEventController.class)

    def taskService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", revisions: "PUT"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekEvent.list(params), model: [tekEventInstanceCount: TekEvent.count()]
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
//        render(contentType: 'text/json') {
//            ['body': "Thank you for Volunteering",
//             'data': "<a href='/TekDays/tekUser/show/${params.id}'>${session.user.fullName}</a>"]
//        }
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

    RevisionsService revisionsService

    def revisions() {
        def revisionList = revisionsService.getRevisionResults(TekEvent.class, params.getLong('id'))
        [revisionList: revisionList, showList: params.showList]
    }

    def revisionSelect() {
        [instance: TekEvent.get(params.id)]
    }
}
