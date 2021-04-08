package com.tekdays

import org.hibernate.envers.RevisionEntity
import org.hibernate.envers.RevisionNumber
import org.hibernate.envers.RevisionTimestamp

@RevisionEntity(UserRevisionListener.class)
class UserRevisionEntity {
    @RevisionNumber
    Long id
    @RevisionTimestamp
    Long timestamp
    TekUser currentUser

    static constraints = {
        currentUser nullable: false
    }

    static transients = ['revisionDate']

    Date getRevisionDate() {
        return new Date(timestamp)
    }
}
