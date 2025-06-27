package com.kgyury.gguigit.dto

import java.time.Instant

data class MusicPostRes(

    val id: Long,
    val text: String,
    val videoUrl: String,
    val imageUrl: String,
    val author: String,
    val createdDate: Instant,
    val rating: Int
)