package com.pratama.samplecoroutineslivedata.data.datasource.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.pratama.samplecoroutineslivedata.data.datasource.remote.AlbumServices
import com.pratama.samplecoroutineslivedata.data.datasource.remote.mapper.AlbumRemoteMapper
import com.pratama.samplecoroutineslivedata.domain.model.Album
import com.pratama.samplecoroutineslivedata.domain.repository.AlbumRepository

internal class AlbumRepositoryImpl(
    private val service: AlbumServices,
    private val albumRemoteMapper: AlbumRemoteMapper
) : AlbumRepository {

    override fun getAlbumByPhrase(phrase: String): LiveData<List<Album>> {
        return liveData {
            val response = service.searchAlbumAsync(phrase)
            emit(albumRemoteMapper.mapToList(response.results.albumMatches.album))
        }
    }

    // override suspend fun getAlbumByPhrase(phrase: String): List<Album> {
    //     val response = service.searchAlbumAsync(phrase)
    //     return albumRemoteMapper.mapToList(response.results.albumMatches.album)
    // }
}