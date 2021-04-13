package com.tekdays

class RevisionsTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def showRevisions = { attrs ->
        List revisionList = (List) attrs.revisionList
        if (revisionList) {
            List keyList = new ArrayList(revisionList[0][0].properties.keySet()).sort()
            boolean[][] checkForDiff = new boolean[revisionList.size()][keyList.size()]
            for (i in 1..<revisionList.size()) {
                for (j in 0..<keyList.size()) {
                    def a1 = revisionList[i][0].properties.get(keyList[j])
                    def a2 = revisionList[i - 1][0].properties.get(keyList[j])
                    if (a1 != a2) {
                        checkForDiff[i][j] = true
                    }
                }
            }

            out << "<table><thead><tr>"
            if (attrs.showList) {
                out << "<th>Compare</th>"
            }
            out << "<th>Version</th>"
            out << "<th>RevId</th>"
            out << "<th>RevType</th>"
            out << "<th>ChangedDate</th>"
            out << "<th>User</th>"
            if (!attrs.showList) {
                keyList.each { key ->
                    out << "<th>${key}</th>"
                }
            } else {
                keyList.each { key ->
                    if (key in attrs.showList) {
                        out << "<th>${key}</th>"
                    }
                }
            }
            out << "</tr></thead><tbody>"

            def classIndex = 0
            for (i in 0..<revisionList.size()) {
                out << "<tr class=${(classIndex++ % 2) == 0 ? 'even' : 'odd'}>"
                if (attrs.showList) {
                    out << "<td><input type='checkbox' name='revId' value=${revisionList[i][1]?.id} /></td>"
                }
                out << "<td>${revisionList[i][0]?.version}</td>"
                out << "<td>${revisionList[i][1]?.id}</td>"
                out << "<td>${revisionList[i][2]}</td>"
                out << "<td>${new Date(revisionList[i][1]?.timestamp).format('yyyy-MM-dd HH:mm')}</td>"
                def user = revisionList[i][1]?.currentUser
                out << "<td>${link(controller: 'tekUser', action: 'show', id: revisionList[i][1]?.currentUserId) { user }}</td>"

                if (!attrs.showList) {
                    for (j in 0..<keyList.size()) {
                        if (checkForDiff[i][j]) {
                            out << "<td class='bg-danger' style='background-color: red'>${revisionList[i][0].properties.get(keyList[j])}</td>"
                        } else {
                            out << "<td>${revisionList[i][0].properties.get(keyList[j])}</td>"
                        }
                    }
                } else {
                    for (j in 0..<keyList.size()) {
                        if (keyList[j] in attrs.showList) {
                            if (checkForDiff[i][j]) {
                                out << "<td class='bg-info'>${revisionList[i][0].properties.get(keyList[j])}</td>"
                            } else {
                                out << "<td>${revisionList[i][0].properties.get(keyList[j])}</td>"
                            }
                        }
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
