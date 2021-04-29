package com.tekdays

import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TaskController {

    // Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class)

    def datatablesSourceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        [properties: [message(code: 'task.title.label'), message(code: 'task.assignedTo.label'), message(code: 'task.dueDate.label'),
                      message(code: 'task.completed.label'), message(code: 'task.notes.label'), message(code: 'task.event.label'),
                      message(code: 'default.button.edit.label'), message(code: "revisions.get.label"), "assignedToId", "eventId"]]
    }

    def dataTablesRenderer() {
        def propertiesToRender = ["title", "assignedTo", "dueDate", "completed", "notes", "event", "id", "id", "assignedToId", "eventId"]
        def entityName = Task.class.simpleName
        render datatablesSourceService.dataTablesSource(propertiesToRender, entityName, params)
    }

    def show(Task taskInstance) {
        // Logger for showing taskInstance
        LOGGER.info("Trying to show taskInstance with id: {} title: {}", taskInstance?.id, taskInstance?.title)

        respond taskInstance
    }

    def create() {
        respond new Task(params)
    }

    @Transactional
    def save(Task taskInstance) {
        if (taskInstance == null) {
            notFound()
            return
        }

        if (taskInstance.hasErrors()) {
            respond taskInstance.errors, view: 'create'
            return
        }

        taskInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'task.label', default: 'Task'), taskInstance.id])
                redirect taskInstance
            }
            '*' { respond taskInstance, [status: CREATED] }
        }
    }

    def edit(Task taskInstance) {
        respond taskInstance
    }

    @Transactional
    def update(Task taskInstance) {
        if (taskInstance == null) {
            notFound()
            return
        }

        if (taskInstance.hasErrors()) {
            respond taskInstance.errors, view: 'edit'
            return
        }

        taskInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'task.label', default: 'Task'), taskInstance.id])
                redirect taskInstance
            }
            '*' { respond taskInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Task taskInstance) {

        if (taskInstance == null) {
            notFound()
            return
        }

        taskInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'task.label', default: 'Task'), taskInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
