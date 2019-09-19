package com.pratama.samplecoroutineslivedata.data.datasource.remote.model

import com.squareup.moshi.Json

enum class ImageSizeRemoteModel {
    @field:Json(name = "medium")
    MEDIUM,
    @field:Json(name = "small")
    SMALL,
    @field:Json(name = "large")
    LARGE,
    @field:Json(name = "extralarge")
    EXTRA_LARGE,
    @field:Json(name = "mega")
    MEGA,
    @field:Json(name = "")
    UNKNOWN
}