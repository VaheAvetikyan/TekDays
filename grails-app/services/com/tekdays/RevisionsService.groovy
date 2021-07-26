package com.tekdays

import grails.transaction.Transactional
import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReaderFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class RevisionsService {
    // Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(RevisionsService.class)

    SessionFactory sessionFactory

    def getRevisionResults(Class<?> className, Long id) {
        def revisionList = []
        AuditReaderFactory
                .get(sessionFactory.currentSession)
                .createQuery()
                .forRevisionsOfEntity(className, false, true)
                .resultList
                .each {
                    if (it[0].id == id) {
                        revisionList << it
                    }
                }
        LOGGER.info("Revision list retrieved for {}, id {}", className, id)
        return revisionList
    }

    def getRevisionResultsById(Class<?> className, Long id, List<Long> revId) {
        def revisionList = []
        AuditReaderFactory
                .get(sessionFactory.currentSession)
                .createQuery()
                .forRevisionsOfEntity(className, false, true)
                .resultList
                .each {
                    if (it[0].id == id && it[1].id in revId) {
                        revisionList << it
                    }
                }
        LOGGER.info("Revision list retrieved for {}, id {}", className, id)
        return revisionList
    }
}
