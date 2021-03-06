package com.tekdays

import org.hibernate.envers.Audited

@Audited
class TekMessage {
    String subject
    String content
    TekMessage parent
    TekEvent event
    TekUser author

    static belongsTo = TekEvent

    static constraints = {
        subject blank: false
        content blank: false
        parent nullable: true
        author nullable: false
    }
}
