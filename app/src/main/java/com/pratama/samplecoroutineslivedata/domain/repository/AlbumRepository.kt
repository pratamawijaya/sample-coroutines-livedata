package com.pratama.samplecoroutineslivedata.domain.repository

import com.pratama.samplecoroutineslivedata.domain.model.Album

interface AlbumRepository {
    fun getAlbumByPhrase(phrase: String): List<Album>
}