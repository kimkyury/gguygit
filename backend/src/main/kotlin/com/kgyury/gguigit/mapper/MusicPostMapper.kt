package com.kgyury.gguigit.mapper

import com.kgyury.gguigit.domain.MusicPost
import com.kgyury.gguigit.dto.*
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class MusicPostMapper {

    fun toRes(entity: MusicPost): MusicPostRes {

        return MusicPostRes(
            text = entity.text,
            videoUrl = entity.videoUrl,
            imageUrl = entity.imageUrl,
            author = entity.author,
            createdDate = entity.createdDate,
            rating = entity.rating
        )
    }

    fun toEntity(dto : MusicPostCreateReq, date : Instant) : MusicPost {

        return MusicPost(
            text = dto.text,
            videoUrl = dto.videoUrl,
            imageUrl = dto.imageUrl,
            author = dto.author,
            createdDate = date,
            updatedDate = date,
            rating = dto.rating
        )
    }

    fun toEntity(entity: MusicPost, dto: MusicPostUpdateReq, date: Instant): MusicPost {
        return entity.copy(
            text = dto.text,
            videoUrl = dto.videoUrl,
            imageUrl = dto.imageUrl,
            author = dto.author,
            updatedDate = date,
            rating = dto.rating
        )
    }
}