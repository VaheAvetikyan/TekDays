package com.tekdays

import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TekUserController {

    // Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(TekUserController.class)

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekUser.list(params), model: [tekUserInstanceCount: TekUser.count()]
    }

    def show(Long id) {
        def tekUserInstance
        if (params.userName) {
            tekUserInstance = TekUser.findByUserName(params.userName)
        } else {
            tekUserInstance = TekUser.get(params.id)
        }
        if (!tekUserInstance) {
            if (params.userName) {
                flash.message = "User not found with username ${params.userName}"
            } else {
                flash.message = "User not found with id $id"
            }
            redirect(action: "index")
            return
        }
        respond tekUserInstance
    }

    def login() {
        if (params.cName) {
            return [cName: params.cName, aName: params.aName, id: params.id]
        }
    }

    def logout() {
        session.user = null
        redirect(uri: '/')
    }

    def validate() {
        def user = TekUser.findByUserName(params.username)
        if (user && user.password == params.password) {
            // Activate user in session
            session.user = user

            // Show login info in log
            LOGGER.info("Successfully logged in user: ${user.fullName}")

            if (params.cName) {

                // Redirect to 'delete' action is forbidden, so we need to redirect to 'show' action
                if (params.aName == "delete") {
                    redirect controller: params.cName, action: 'show', id: params.id
                    // Need to return from method after the redirect above
                    return
                }
                redirect controller: params.cName, action: params.aName, id: params.id
            } else {
                redirect uri: '/'
            }
        } else {
            flash.message = "Invalid username and password."
            render view: 'login'
        }
    }

    def create() {
        respond new TekUser(params)
    }

    @Transactional
    def save(TekUser tekUserInstance) {
        if (tekUserInstance == null) {
            notFound()
            return
        }

        if (tekUserInstance.hasErrors()) {
            respond tekUserInstance.errors, view: 'create'
            return
        }

        tekUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect tekUserInstance
            }
            '*' { respond tekUserInstance, [status: CREATED] }
        }
    }

    def edit(TekUser tekUserInstance) {
        respond tekUserInstance
    }

    @Transactional
    def update(TekUser tekUserInstance) {
        if (tekUserInstance == null) {
            notFound()
            return
        }

        if (tekUserInstance.hasErrors()) {
            respond tekUserInstance.errors, view: 'edit'
            return
        }

        tekUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect tekUserInstance
            }
            '*' { respond tekUserInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekUser tekUserInstance) {

        if (tekUserInstance == null) {
            notFound()
            return
        }

        tekUserInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
