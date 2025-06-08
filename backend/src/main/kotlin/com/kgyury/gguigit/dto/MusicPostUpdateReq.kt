package com.kgyury.gguigit.dto

data class MusicPostUpdateReq(
    val id: Long,
    val text: String,
    val videoUrl: String,
    val imageUrl: String,
    val author: String,
    val rating: Int
)