package com.pratama.samplecoroutineslivedata.data.datasource.remote.mapper

import com.pratama.samplecoroutineslivedata.base.Mapper
import com.pratama.samplecoroutineslivedata.data.datasource.remote.model.ImageRemoteModel
import com.pratama.samplecoroutineslivedata.domain.model.Image

class ImageRemoteMapper : Mapper<ImageRemoteModel, Image> {
    override fun map(from: ImageRemoteModel): Image {
        return Image(
            url = from.url,
            size = ""
        )
    }
}