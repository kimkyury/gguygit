package com.kgyury.gguigit.dto

data class MusicPostCreateReq(
    val text: String,
    val videoUrl: String,
    val imageUrl: String,
    val author: String,
    val rating: Int
)