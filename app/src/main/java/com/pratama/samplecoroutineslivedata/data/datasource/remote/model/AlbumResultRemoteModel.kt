package com.pratama.samplecoroutineslivedata.data.datasource.remote.model

import com.squareup.moshi.Json

internal data class AlbumResultRemoteModel(
    @field:Json(name = "albummatches")
    val albumMatches: AlbumListRemoteModel
)