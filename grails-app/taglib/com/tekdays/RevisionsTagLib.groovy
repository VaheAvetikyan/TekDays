package com.tekdays

class RevisionsTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def showRevisions = { attrs ->
        if (attrs.revisionList) {
            List revisionList = (List) attrs.revisionList
            createRevisionsTable(revisionList, attrs.showList, attrs.dif)
        } else {
            out << "<h1>No Revisions for the entity</h1>"
        }
    }

    private void createRevisionsTable(List revisionList, showList, dif) {
        List keyList = new ArrayList(revisionList[0][0].properties.keySet()).sort()
        boolean[][] checkRevisionDiff = new boolean[revisionList.size()][keyList.size()]
        for (i in 1..<revisionList.size()) {
            for (j in 0..<keyList.size()) {
                def property1 = revisionList[i][0].properties.get(keyList[j])
                def property2 = revisionList[i - 1][0].properties.get(keyList[j])
                if (property1 != property2 && property1.toString() != property2.toString()) {
                    checkRevisionDiff[i][j] = true
                }
            }
        }

        out << "<table><thead><tr>"
        if (!dif) {
            out << "<th>Compare</th>"
        }
        out << "<th>Version</th>"
        out << "<th>RevId</th>"
        out << "<th>RevType</th>"
        out << "<th>ChangedDate</th>"
        out << "<th>User</th>"
        if (!showList) {
            keyList.each { key ->
                out << "<th>${key}</th>"
            }
        } else {
            keyList.each { key ->
                if (key in showList) {
                    out << "<th>${key}</th>"
                }
            }
        }
        out << "</tr></thead><tbody>"

        def classIndex = 0
        for (i in 0..<revisionList.size()) {
            out << "<tr class=${(classIndex++ % 2) == 0 ? 'even' : 'odd'}>"
            if (!dif) {
                out << "<td><input type='checkbox' name='revId' value=${revisionList[i][1]?.id} /></td>"
            }
            out << "<td>${revisionList[i][0]?.version}</td>"
            out << "<td>${revisionList[i][1]?.id}</td>"
            out << "<td>${revisionList[i][2]}</td>"
            out << "<td>${new Date(revisionList[i][1]?.timestamp).format('yyyy-MM-dd HH:mm')}</td>"
            def user = revisionList[i][1]?.currentUser
            out << "<td>${link(controller: 'tekUser', action: 'show', id: revisionList[i][1]?.currentUserId) { user }}</td>"

            if (!showList) {
                for (j in 0..<keyList.size()) {
                    if (checkRevisionDiff[i][j]) {
                        out << "<td class='bg-danger rev-diff-change-color'>${revisionList[i][0].properties.get(keyList[j])}</td>"
                    } else {
                        out << "<td>${revisionList[i][0].properties.get(keyList[j])}</td>"
                    }
                }
            } else {
                for (j in 0..<keyList.size()) {
                    if (keyList[j] in showList) {
                        if (checkRevisionDiff[i][j]) {
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
    }
}
