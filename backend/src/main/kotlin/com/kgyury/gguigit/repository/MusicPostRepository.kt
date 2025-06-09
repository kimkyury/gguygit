package com.kgyury.gguigit.repository

import com.kgyury.gguigit.domain.MusicPost
import org.springframework.data.jpa.repository.JpaRepository

interface MusicPostRepository : JpaRepository<MusicPost, Long>{

}