package com.pratama.samplecoroutineslivedata.base

interface Mapper<in FROM, out TO> {
    fun map(from: FROM): TO

    fun mapToList(froms: List<FROM>): List<TO> {
        val listTo = mutableListOf<TO>()
        froms.map { listTo.add(map(it)) }
        return listTo
    }
}