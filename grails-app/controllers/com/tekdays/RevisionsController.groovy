package com.tekdays

class RevisionsController {

    RevisionsService revisionsService

    def revisions() {
        Class cls = Class.forName(params.type)
        def revisionList = revisionsService.getRevisionResults(cls, params.getLong('id'))
        [revisionList: revisionList, showList: params.showList]
    }

    def revisionSelect() {
        if (!params.type) {
            redirect(uri: '/')
            return
        }
        Class cls = Class.forName(params.type)
        def instance = cls.get(params.id)
        [instance: instance]
    }

    def compare() {
        Class cls = Class.forName(params.type)
        def revisionList = revisionsService.getRevisionResults(cls, params.getLong('id'), (params.revId.collect{ Long.valueOf(it) } ))
        [revisionList: revisionList]
    }
}
