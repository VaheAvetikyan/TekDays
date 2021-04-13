package com.tekdays

import org.hibernate.envers.Audited

@Audited
class TekEvent {

    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description
    String nickname

    static hasMany = [volunteers  : TekUser,
                      respondents : String,
                      sponsorships: Sponsorship,
                      tasks       : Task,
                      messages    : TekMessage]

    @Override
    String toString() {
        "$name, $city"
    }

    static constraints = {
        name nullable: false, blank: false
        city nullable: false, blank: false
        description maxSize: 5000
        organizer nullable: false
        venue nullable: false, blank: false
        startDate blank: false
        endDate blank: false,
                // Custom validator for end date to be after start date
                validator: { date, obj -> !date.before(obj.startDate) }
        sponsorships nullable: true
        volunteers nullable: true
        respondents blank: true
        tasks nullable: true
        messages nullable: true
        nickname nullable: true, unique: true
    }

    static mapping = {
        sponsorships cascade: 'all-delete-orphan'
        tasks cascade: 'all-delete-orphan'
        messages cascade: 'all-delete-orphan'
        organizer lazy: false
        volunteers lazy: false
    }
}
