package br.com.app.movie.data.mapper

import br.com.app.movie.data.model.Cast
import br.com.app.movie.data.model.Credits
import br.com.app.movie.data.model.Crew
import br.com.app.movie.data.remote.dto.CreditsDto
import kotlin.collections.map

fun CreditsDto.toCredits(): Credits {
    return Credits(
        cast = this.cast.map { castDto ->
            Cast(
                originalName = castDto.originalName,
                profilePath = castDto.profilePath,
                character = castDto.character
            )
        },
         crew = this.crew.map { crewDto ->
             Crew(
                 originalName = crewDto.originalName,
                 profilePath = crewDto.profilePath,
                 job = crewDto.job
             )
         }
    )
}