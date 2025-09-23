package br.com.app.movie.data.mapper

import br.com.app.movie.data.model.Video
import br.com.app.movie.data.remote.dto.VideoDto

fun VideoDto.toVideo(): Video {
    return Video(
        name = this.name,
        key = this.key,
        site = this.site,
        type = this.site
    )
}