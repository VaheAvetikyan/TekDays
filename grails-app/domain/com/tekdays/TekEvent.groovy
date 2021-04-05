package com.tekdays

class TekEvent {

    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description

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
        name()
        city()
        description maxSize: 5000
        organizer()
        venue()
        startDate blank: false
        endDate blank: false,
                // Custom validator for end date to be after start date
                validator: { date, obj -> !date.before(obj.startDate) }
        sponsorships nullable: true
        volunteers nullable: true
        respondents blank: true
        tasks nullable: true
        messages nullable: true
    }

    static mapping = {
        sponsorships cascade: 'all-delete-orphan'
        tasks cascade: 'all-delete-orphan'
        messages cascade: 'all-delete-orphan'
    }
}
