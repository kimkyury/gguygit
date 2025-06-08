package com.kgyury.gguigit.controller

import com.kgyury.gguigit.dto.MusicPostCreateReq
import com.kgyury.gguigit.dto.MusicPostRes
import com.kgyury.gguigit.dto.MusicPostUpdateReq
import com.kgyury.gguigit.service.MusicPostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/music-posts")
class MusicPostController (
    private val musicPostService : MusicPostService
){

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): ResponseEntity<MusicPostRes> {
        val response = musicPostService.getPostById(id)
        return ResponseEntity.ok(response)
    }


    @GetMapping
    fun getAllPosts(): ResponseEntity<List<MusicPostRes>> {
        val responses = musicPostService.getAllPosts()
        return ResponseEntity.ok(responses)
    }

    @PostMapping
    fun createPost(@RequestBody request: MusicPostCreateReq): ResponseEntity<MusicPostRes> {
        val response = musicPostService.createPost(request)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody request: MusicPostUpdateReq): ResponseEntity<MusicPostRes> {
        val response = musicPostService.updatePost(id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Void> {
        musicPostService.deletePost(id)
        return ResponseEntity.noContent().build()
    }
}