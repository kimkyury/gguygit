package com.kgyury.gguigit.domain

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "music_posts")
data class MusicPost (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long =0,

    @Column(name = "text")
    val text: String,

    @Column(name = "video_url")
    val videoUrl : String,

    @Column(name = "image_url")
    val imageUrl: String,

    @Column(name = "author")
    val author: String,

    @Column(name = "created_date")
    val createdDate: Instant, /* UTC 기준 시간 저장 */

    @Column(name = "updated_date")
    val updatedDate: Instant, /* UTC 기준 시간 저장 */

    @Column(name = "rating")
    val rating: Int
)