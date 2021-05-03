package com.tekdays

import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class SchedulerService {
    private final Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class)

    def periodicRun() {
        LOGGER.info("Runned at {}", new Date().format("EEE, MMM d, yyyy, HH:mm:ss"))
    }
}
