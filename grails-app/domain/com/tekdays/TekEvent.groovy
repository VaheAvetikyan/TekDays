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
        startDate()
        endDate()
        sponsorships nullable: true
        volunteers nullable: true
        respondents blank: true
        tasks nullable: true
        messages nullable: true
    }
}
