package com.tekdays

class JasperCustomTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def jasperCustom = { attrs ->
        out << jasperReport(jasper: attrs.entityName,
                format: "PDF,CSV,XLS,HTML",
                description: message(code: "jasper.download.label", args: [attrs.entityName]),
                name: "${attrs.entityName}List")
    }

    def jasperMail = { attrs ->
        out << "<p>${message(code: 'jasper.mail.send.label')}</p>"
        out << g.link("<img src='/TekDays/plugins/jasper-1.10.0/images/icons/PDF.gif'>", action: " sendJasperEmail ", params: [format: 'PDF'])
        out << g.link("<img src='/TekDays/plugins/jasper-1.10.0/images/icons/CSV.gif'>", action: " sendJasperEmail ", params: [format: 'CSV'])
        out << g.link("<img src='/TekDays/plugins/jasper-1.10.0/images/icons/XLS.gif'>", action: " sendJasperEmail ", params: [format: 'XLS'])
    }
}
