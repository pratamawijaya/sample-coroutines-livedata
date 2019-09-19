package com.pratama.samplecoroutineslivedata.domain.repository

import androidx.lifecycle.LiveData
import com.pratama.samplecoroutineslivedata.domain.model.Album

interface AlbumRepository {
    fun getAlbumByPhrase(phrase: String): LiveData<List<Album>>
}