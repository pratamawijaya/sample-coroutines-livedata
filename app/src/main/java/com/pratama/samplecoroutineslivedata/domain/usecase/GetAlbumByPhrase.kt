package com.pratama.samplecoroutineslivedata.domain.usecase

import androidx.lifecycle.LiveData
import com.pratama.samplecoroutineslivedata.domain.model.Album
import com.pratama.samplecoroutineslivedata.domain.repository.AlbumRepository

class GetAlbumByPhrase(private val repository: AlbumRepository) {

    fun execute(phrase: String): LiveData<List<Album>> {
        return repository.getAlbumByPhrase(phrase)
    }
}