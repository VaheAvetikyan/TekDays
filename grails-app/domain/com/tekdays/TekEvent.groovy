package com.tekdays

class TekEvent {

    String city
    String name
    String organizer
    String venue
    Date startDate
    Date endDate
    String description

    @Override
    String toString() {
        "$name, $city"
    }
    static constraints = {
    }
}
