package com.pratama.samplecoroutineslivedata.data.datasource.remote.model

import com.squareup.moshi.Json

internal data class AlbumRemoteModel(
    val name: String,
    val artist: String,
    @field:Json(name = "image") val images: List<ImageRemoteModel>?
)