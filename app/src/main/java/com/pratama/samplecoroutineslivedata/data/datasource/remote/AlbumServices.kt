package com.pratama.samplecoroutineslivedata.data.datasource.remote

import com.pratama.samplecoroutineslivedata.data.datasource.remote.response.SearchAlbumResponse
import retrofit2.http.POST
import retrofit2.http.Query

internal interface AlbumServices {
    @POST("./?method=album.search")
    suspend fun searchAlbumAsync(
        @Query("album") phrase: String,
        @Query("limit") limit: Int = 60
    ): SearchAlbumResponse
}