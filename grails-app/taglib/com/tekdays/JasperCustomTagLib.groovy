package com.tekdays

class JasperCustomTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def jasperCustom = { attrs ->
        out << jasperReport(jasper: attrs.entityName,
                format: "CSV,XLS,PDF,HTML",
                description: "Download ${attrs.entityName} Report",
                name: "${attrs.entityName}List")
    }
}
