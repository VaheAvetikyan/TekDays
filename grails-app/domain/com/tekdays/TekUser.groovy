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
        fullName()
        userName()
        email email: true
        website()
        bio maxSize: 5000
    }
}
