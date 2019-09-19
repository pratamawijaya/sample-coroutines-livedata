package com.pratama.samplecoroutineslivedata.data.datasource.remote.repository

import androidx.lifecycle.liveData
import com.pratama.samplecoroutineslivedata.data.datasource.remote.AlbumServices
import com.pratama.samplecoroutineslivedata.data.datasource.remote.mapper.AlbumRemoteMapper
import com.pratama.samplecoroutineslivedata.domain.repository.AlbumRepository

internal class AlbumRepositoryImpl(
    private val service: AlbumServices,
    private val albumRemoteMapper: AlbumRemoteMapper
) : AlbumRepository {

    override fun getAlbumByPhrase(phrase: String) = liveData {
        val response = service.searchAlbumAsync(phrase)
        emit(albumRemoteMapper.mapToList(response.results.albumMatches.album))
    }

    // W I P
    // override fun getAlbumByPhrase(phrase: String): LiveData<List<Album>>  = resultLiveData(
    //     databaseQuery = getDataFromDb(),
    //     networkCall = getDataFromNetwork(),
    //     saveCallResult = saveDataToDb()
    // )
    //
    // private fun saveDataToDb(): Any {
    //     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    // }
    //
    // private fun getDataFromNetwork(): Any {
    //     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    // }
    //
    // private fun getDataFromDb(): Any {
    //     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    // }
}