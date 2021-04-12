package com.tekdays

class RevisionsController {

    RevisionsService revisionsService

    def revisions() {
        def revisionList = revisionsService.getRevisionResults(Class.forName(params.type), params.getLong('id'))
        [revisionList: revisionList, showList: params.showList]
    }

    def revisionSelect() {
        def cls = Class.forName(params.type)
        def instance = cls.get(params.id)
        [instance: instance]
    }
}
