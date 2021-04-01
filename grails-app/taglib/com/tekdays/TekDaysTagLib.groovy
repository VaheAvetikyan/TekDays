package com.tekdays

class TekDaysTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def messageThread = { attrs ->
        def messages = attrs.messages.findAll { msg -> !msg.parent }
        processMessages(messages, 0)
    }

    void processMessages(messages, indent) {
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
        out << "<div style='margin: 15px 0 40px;'>"
        if (request.getSession(false) && session.user) {
            out << "<span id='loginToggle'>Welcome "
            out << "<a href='${createLink(controller: 'tekUser', action: 'show', id: session.user.id)}'>"
            out << "${session.user}.</a>"
            out << "</span><span id='loginLogout'>"
            out << "<a href='${createLink(controller: 'tekUser', action: 'logout')}'>"
            out << "Logout </a></span>"
        } else {
            out << "<span id='loginLogout'>"
            out << "<a href='${createLink(controller: 'tekUser', action: 'login')}'>"
            out << "Login </a></span>"
        }
        out << "</div><br/>"
    }
}
