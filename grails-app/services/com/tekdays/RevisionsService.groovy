package com.tekdays

import grails.transaction.Transactional
import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.query.AuditQuery

@Transactional
class RevisionsService {
    SessionFactory sessionFactory

    def serviceMethod() {
    }

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
        return revisionList
    }
}
