package com.tekdays

class TekDaysTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def messageThread = { attrs ->
        def messages = attrs.messages.findAll { msg -> !msg.parent }
        processMessages(messages, 0)
    }

    private void processMessages(messages, indent) {
        messages.each { msg ->
            def body = "${msg?.author} - ${msg?.subject}"
            out << "<p style='height:35; margin-left:${indent * 20}px;'>"
            out << "${remoteLink(action: 'showDetail', id: msg.id, update: 'details', body)}"
            out << "</p>"
            def children = TekMessage.findAllByParent(msg)
            if (children) {
                processMessages(children, indent + 1)
            }
        }
    }

    def loginToggle = {
        out << "<div class='authenticationBar'>"
        if (request.getSession(false) && session.user) {
            out << "<div class='loginToggle' id='loginWelcome'>${message(code: 'default.login-logout.welcome')} "
            out << "<a href='${createLink(controller: 'tekUser', action: 'show', id: session.user.id)}'>"
            out << "${session.user}.</a>"
            out << "</div><div class='loginToggle' id='loginLogout'>"
            out << "<a href='${createLink(controller: 'tekUser', action: 'logout')}'>"
            out << "${message(code: 'default.logout.label')}</a></div>"
        } else {
            out << "<div id='loginLogout'>"
            out << "<a href='${createLink(controller: 'tekUser', action: 'login')}'>"
            out << "${message(code: 'default.login.label')}</a>"
            out << "<br>"
            out << "<a href='${createLink(controller: 'tekUser', action: 'register')}'>"
            out << "${message(code: 'default.register.label')}</a></div>"
        }
        out << "</div><br/>"
    }

    def organizerEvents = {
        if (request.getSession(false) && session.user) {
            def events = TekEvent.findAllByOrganizer(session.user)
            if (events) {
                return eventListLinkBuilder(events, message(code: 'default.homepage.isOrganizing'))
            }
        }
    }

    def volunteerEvents = {
        if (request.getSession(false) && session.user) {
            def events = TekEvent.createCriteria().list {
                volunteers {
                    eq('id', session.user?.id)
                }
            }
            return eventListLinkBuilder(events, message(code: 'default.homepage.volunteered'))
        }
    }

    private String eventListLinkBuilder(events, methodName) {
        out << "<div class='page-body'>"
        out << "<h1>${session.user.fullName.split()[0]} ${methodName}:</h1>"
        out << "<ul>"
        events.each {
            out << "<li><a href='"
            out << "${createLink(controller: 'tekEvent', action: 'show', id: it.id)}'>"
            out << "${it}</a></li>"
        }
        out << "</ul>"
        out << "</div>"
    }

    def volunteerButton = {attrs ->
        if (request.getSession(false) && session.user){
            def user = session.user.merge()
            def event = TekEvent.get(attrs.eventId)
            if (event && !event.volunteers.contains(user)){
                out << "<span id='volunteerSpan' class='menuButton'>"
                out << "<button id='volunteerButton' type='button'>"
                out << "${message(code:'tekEvent.volunteerFor')}"
                out << "</button>"
                out << "</span>"
            }
        }
    }
}
