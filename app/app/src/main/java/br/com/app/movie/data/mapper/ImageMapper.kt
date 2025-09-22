package br.com.app.movie.data.mapper

import br.com.app.movie.data.model.Image
import br.com.app.movie.data.remote.dto.ImageDto

fun ImageDto.toImage(): Image {
    return Image(
        filePath = this.filePath
    )
}