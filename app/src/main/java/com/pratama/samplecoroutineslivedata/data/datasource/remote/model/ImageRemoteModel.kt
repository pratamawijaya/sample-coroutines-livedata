package com.pratama.samplecoroutineslivedata.data.datasource.remote.model

import com.squareup.moshi.Json

data class ImageRemoteModel(
    @field:Json(name = "#text") val url: String,
    val size: ImageSizeRemoteModel
)