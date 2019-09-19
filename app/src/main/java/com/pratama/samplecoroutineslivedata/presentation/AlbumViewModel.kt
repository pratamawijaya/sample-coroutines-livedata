package com.pratama.samplecoroutineslivedata.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pratama.samplecoroutineslivedata.domain.model.Album
import com.pratama.samplecoroutineslivedata.domain.usecase.GetAlbumByPhrase

class AlbumViewModel(private val useCase: GetAlbumByPhrase) : ViewModel() {

    val currentAlbum: LiveData<List<Album>> = liveData {
        emit(emptyList<Album>())
        emitSource(useCase.execute("american"))
    }
}