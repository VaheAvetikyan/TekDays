package com.tekdays

class RevisionsTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def showRevisions = { attrs ->
        def revisionList = attrs.revisionList
        List keySet = new ArrayList(revisionList[0][0].properties.keySet())
        if (revisionList) {
            out << """
            <table><thead><tr>
            <th>Version</th>
            <th>RevId</th>
            <th>RevType</th>
            <th>ChangedDate</th>
            <th>User</th>"""
            keySet.each { k ->
                if (k in attrs.showList) {
                    out << "<th>${k}</th>"
                }
            }
            out << "</tr></thead><tbody>"
            def classIndex = 0
            revisionList.each {
                out << """<tr class="${(classIndex++ % 2) == 0 ? 'even' : 'odd'}">
                <td>${it[0]?.version}</td>
                <td>${it[1]?.id}</td>
                <td>${it[2]}</td>
                <td>${new Date(it[1]?.timestamp).format('yyyy-MM-dd HH:mm')}</td>"""
                def user = it[1]?.currentUser
                out << "<td>${link(controller: 'tekUser', action: 'show', id: it[1]?.currentUserId) { user }}</td>"
                keySet.each { k ->
                    if (k in attrs.showList) {
                        out << "<td>${it[0].properties."${k}"}</td>"
                    }
                }
                out << "</tr>"
            }
            out << "</tbody></table>"
        } else {
            out << "<h1>No Revisions for the entity</h1>"
        }
    }
}
