package com.tekdays

import grails.converters.JSON
import grails.plugin.mail.MailService
import grails.transaction.Transactional
import groovy.json.JsonBuilder
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.codehaus.groovy.grails.plugins.jasper.JasperService

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class SponsorController {

    JasperService jasperService
    MailService mailService

    static allowedMethods = [save: "POST", delete: "DELETE", apiData: "GET"] // update: "PUT",

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sponsor.list(params), model: [sponsorInstanceCount: Sponsor.count()]
    }

    def sendJasperEmail(String format) {
        def report = jasperService.generateReport(new JasperReportDef(name: Sponsor.class.simpleName, fileFormat: JasperExportFormat."${format}_FORMAT"))
        mailService.sendMail {
            multipart true
            to session.user.email
            subject "TekDays.com ${Sponsor.class.simpleName} Report"
            html g.render(template: "../jasperMail")
            attachBytes "${Sponsor.class.simpleName}List.${format.toLowerCase()}", "application/${format.toLowerCase()}", report.toByteArray()
        }
        redirect action: "index"
    }

    def show(Sponsor sponsorInstance) {
        respond sponsorInstance
    }

    def create() {
        respond new Sponsor(params)
    }

    @Transactional
    def save(Sponsor sponsorInstance) {
        if (sponsorInstance == null) {
            notFound()
            return
        }

        if (sponsorInstance.hasErrors()) {
            respond sponsorInstance.errors, view: 'create'
            return
        }

        sponsorInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), sponsorInstance.id])
                redirect sponsorInstance
            }
            '*' { respond sponsorInstance, [status: CREATED] }
        }
    }

    def edit(Sponsor sponsorInstance) {
        respond sponsorInstance
    }

    @Transactional
    def update(Sponsor sponsorInstance) {
        if (sponsorInstance == null) {
            notFound()
            return
        }

        if (sponsorInstance.hasErrors()) {
            respond sponsorInstance.errors, view: 'edit'
            return
        }

        sponsorInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), sponsorInstance.id])
                redirect sponsorInstance
            }
            '*' { respond sponsorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Sponsor sponsorInstance) {

        if (sponsorInstance == null) {
            notFound()
            return
        }

        sponsorInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), sponsorInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def fetchSponsorImage() {
        def sponsor = Sponsor.findByName(params.name)
        byte[] imageInByte = sponsor.logo
        response.contentType = 'image/png' // or the appropriate image content type
        response.outputStream << imageInByte
        response.outputStream.flush()
    }

    def apiData() {
        def data = Sponsor.get(params.id)
        if (data) {
            def builder = new JsonBuilder()
            def root = builder.sponsor {
                name data.name
                website data.website
                description data.description
            }
            render root as JSON
        } else {
            data = Sponsor.list()
            def jsonBuilder = new JsonBuilder()
            def root = jsonBuilder.sponsors {
                sponsor data.collect {
                    [name       : it.name,
                     website    : it.website,
                     description: it.description]
                }
            }
            render root as JSON
        }
    }
}
