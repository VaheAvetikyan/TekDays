package com.tekdays

class JasperCustomTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def jasperCustom = { attrs ->
        out << jasperReport(jasper: attrs.entityName,
                format: "CSV,XLS,PDF,HTML",
                description: message(code: "jasper.download.label", args: [attrs.entityName]),
                name: "${attrs.entityName}List")
    }
}
