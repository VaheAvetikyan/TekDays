package com.tekdays

class RevisionsTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def showRevisions = { attrs ->
        def revisionList = attrs.revisionList

        revisionList.each {
            out << '''<table><thead><tr>
                    <th>RevId</th>
                    <th>RevType</th>
                    <th>Version</th>
                    <th>ChangedDate</th>
                    <th>User</th>
                    </tr></thead>'''
            out << "<tbody><tr>"
            out << "<td>${it[1].id}</td>"
            out << "<td>${it[2]}</td>"
            out << "<td>${it[1].version}</td>"
            out << "<td>${UserRevisionEntity.read(it[1]?.id)?.revisionDate?.format('yyyy-MM-dd HH:mm')}</td>"
            out << "<td>${UserRevisionEntity.read(it[1]?.id)?.currentUser}</td>"
            out << "</tr></tbody><thead><tr>"
            it[0].properties.each {
                out << "<th>${it.key}</th>"
            }
            out << "</tr></thead><tbody><tr>"
            it[0].properties.each {
                out << "<td>${it.value}</td>"
            }
            out << "</tr></tbody></table>"
        }
    }
}
