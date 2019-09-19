package com.pratama.samplecoroutineslivedata.data.datasource.remote.mapper

import com.pratama.samplecoroutineslivedata.base.Mapper
import com.pratama.samplecoroutineslivedata.data.datasource.remote.model.AlbumRemoteModel
import com.pratama.samplecoroutineslivedata.domain.model.Album

class AlbumRemoteMapper(private val imageRemoteMapper: ImageRemoteMapper) :
    Mapper<AlbumRemoteModel, Album> {
    override fun map(from: AlbumRemoteModel): Album {
        return with(from) {

            val imgsModel = images?.let {
                imageRemoteMapper.mapToList(images)
            }

            Album(
                name = name,
                articst = artist,
                images = imgsModel ?: emptyList()
            )
        }
    }
}