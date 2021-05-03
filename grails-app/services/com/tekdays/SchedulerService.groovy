package com.tekdays

import grails.plugin.mail.MailService
import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class SchedulerService {
    private final Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class)

    MailService mailService

    void upcomingEventsEmail() {
        LOGGER.info("Email initiated to be sent at {}", new Date().format("EEE, MMM d, yyyy, HH:mm:ss"))
        def tekEvents = TekEvent.findAllByStartDateBetween(new Date(), new Date() + 7, [order: "desc"])

        if (tekEvents) {
            def emails = TekUser.findAllByEmailIsNotNull().collect { it.email }

            StringBuilder htmlBody = new StringBuilder()
            htmlBody << "<body><div><h1>Events happening this week</h1></div><div>"
            htmlBody << "<table><thead><tr><td>Name</td><td>Location</td><td>Start Date</td><td>End Date</td><td>Description</td></tr></thead><tbody><tr>"
            tekEvents.each {
                htmlBody << "<td>${it.name}</td><td>${it.city}, ${it.venue}</td><td>${it.startDate}</td><td>${it.endDate}</td><td>${it.description}</td>"
            }
            htmlBody << "</tr></tbody></table></div></body>"

            mailService.sendMail {
                to emails
                subject "TekDays.com Upcoming events of the week..."
                html htmlBody.toString()
            }
            LOGGER.info("Email sent at {}", new Date().format("EEE, MMM d, yyyy, HH:mm:ss"))
        }

        LOGGER.info("Email was not sent. No events this week")
    }
}
