package com.tekdays

import grails.converters.JSON
import grails.plugin.mail.MailService
import grails.transaction.Transactional
import groovy.json.JsonBuilder
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.codehaus.groovy.grails.plugins.jasper.JasperService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class SponsorshipController {

    // Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(SponsorshipController.class)

    JasperService jasperService
    MailService mailService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", apiData: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sponsorship.list(params), model: [sponsorshipInstanceCount: Sponsorship.count()]
    }

    def sendJasperEmail(String format) {
        def report = jasperService.generateReport(new JasperReportDef(name: Sponsorship.class.simpleName, fileFormat: JasperExportFormat."${format}_FORMAT"))
        mailService.sendMail {
            multipart true
            to session.user.email
            subject "TekDays.com ${Sponsorship.class.simpleName} Report"
            html g.render(template: "../jasperMail")
            attachBytes "${Sponsorship.class.simpleName}List.${format.toLowerCase()}", "application/${format.toLowerCase()}", report.toByteArray()
        }

        // Log email
        LOGGER.info("Sponsorships {} report was sent to {}", format, session.user.email)

        redirect action: "index"
    }

    def show(Sponsorship sponsorshipInstance) {
        respond sponsorshipInstance
    }

    def create() {
        respond new Sponsorship(params)
    }

    @Transactional
    def save(Sponsorship sponsorshipInstance) {
        if (sponsorshipInstance == null) {
            notFound()
            return
        }

        if (sponsorshipInstance.hasErrors()) {
            respond sponsorshipInstance.errors, view: 'create'
            return
        }

        sponsorshipInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), sponsorshipInstance.id])
                redirect sponsorshipInstance
            }
            '*' { respond sponsorshipInstance, [status: CREATED] }
        }
    }

    def edit(Sponsorship sponsorshipInstance) {
        respond sponsorshipInstance
    }

    @Transactional
    def update(Sponsorship sponsorshipInstance) {
        if (sponsorshipInstance == null) {
            notFound()
            return
        }

        if (sponsorshipInstance.hasErrors()) {
            respond sponsorshipInstance.errors, view: 'edit'
            return
        }

        sponsorshipInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), sponsorshipInstance.id])
                redirect sponsorshipInstance
            }
            '*' { respond sponsorshipInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Sponsorship sponsorshipInstance) {

        if (sponsorshipInstance == null) {
            notFound()
            return
        }

        sponsorshipInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), sponsorshipInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def apiData() {
        def data = Sponsorship.get(params.id)
        if (data) {
            def builder = new JsonBuilder()
            def root = builder.sponsorship {
                event data.event.name
                sponsor data.sponsor.name
                contributionType data.contributionType
                description data.description
                notes data.notes
            }
            render root as JSON
        } else {
            data = Sponsorship.list()
            def builder = new JsonBuilder()
            def root = builder.sponsorships {
                sponsorship data.collect {
                    [event           : it.event.name,
                     sponsor         : it.sponsor.name,
                     contributionType: it.contributionType,
                     description     : it.description,
                     notes           : it.notes]
                }
            }
            render root as JSON
        }
    }
}
