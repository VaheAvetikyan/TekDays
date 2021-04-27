package com.tekdays

class DashboardController {

    def index() {}

    def dashboard = {
        def event = TekEvent.get(params.id)
        if (event) {
            if (event.organizer.userName == session.user.userName ||
                    event.volunteers.collect { it.userName }.contains(session.user.userName)) {
                def tasks = Task.findAllByEventAndCompleted(event, false, [max: 3, sort: 'dueDate', order: 'asc'])
                def volunteers = event.volunteers
                def messages = TekMessage.findAllByEventAndParentIsNull(event, [sort: 'id', order: 'desc'])
                def sponsorships = event.sponsorships
                return [event       : event,
                        tasks       : tasks,
                        volunteers  : volunteers,
                        messages    : messages,
                        sponsorships: sponsorships]
            } else {
                flash.message = message(code: "dashboard.access.denied.label", args: [event.name])
                redirect controller: 'tekEvent', action: 'index'
            }
        } else {
            flash.message = message(code: "dashboard.noEventFound.label", args: [params.id])
            redirect controller: 'tekEvent', action: 'index'
        }
    }
}
