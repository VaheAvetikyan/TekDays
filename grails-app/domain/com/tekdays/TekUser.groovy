package com.tekdays

class TekUser {

    String fullName
    String userName
    String password
    String email
    String website
    String bio

    @Override
    String toString() {
        fullName
    }

    static constraints = {
        fullName blank: false
        userName blank: false, nullable: false, unique: true
        email email: true, nullable: false, unique: true
        website url: true, nullable: true
        bio maxSize: 5000
    }
}
