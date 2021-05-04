package com.tekdays

import grails.gsp.PageRenderer
import grails.plugin.mail.MailService
import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class SchedulerService {
    private final Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class)

    MailService mailService
    PageRenderer groovyPageRenderer

    void upcomingEventsEmail() {
        LOGGER.info("Emails initiated to be sent at {}", new Date().format("EEE, MMM d, yyyy, HH:mm:ss"))
        def events = TekEvent.findAllByStartDateBetween(new Date(), new Date() + 365, [order: "desc"])

        if (events) {
            def emails = TekUser.findAllByEmailIsNotNull().collect { it.email }
            mailService.sendMail {
                to emails
                subject "TekDays.com Upcoming events of the week..."
                html groovyPageRenderer.render(template: "../upcomingEvents", model: [events: events])
            }
            LOGGER.info("Email sent at {}", new Date().format("EEE, MMM d, yyyy, HH:mm:ss"))
            return
        }
        LOGGER.info("Email was not sent. No events this week")
    }
}
