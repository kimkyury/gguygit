package com.kgyury.gguigit.service

import com.kgyury.gguigit.domain.MusicPost
import com.kgyury.gguigit.dto.MusicPostCreateReq
import com.kgyury.gguigit.dto.MusicPostRes
import com.kgyury.gguigit.dto.MusicPostUpdateReq
import com.kgyury.gguigit.mapper.MusicPostMapper
import com.kgyury.gguigit.repository.MusicPostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.NoSuchElementException

@Service
@Transactional
class MusicPostService (
    private val musicPostRepo: MusicPostRepository,
    private val musicPostMapper: MusicPostMapper
){

    fun createPost(req: MusicPostCreateReq): MusicPostRes {
        val now = Instant.now();


        val entity = musicPostMapper.toEntity(req, now)
        val saved = musicPostRepo.save(entity)

        return musicPostMapper.toRes(saved);
    }

    fun getPostById(id: Long): MusicPostRes {

        val post = getPostByIdOrElseThrow(id);
        return musicPostMapper.toRes(post)
    }

    fun updatePost(id: Long, req: MusicPostUpdateReq): MusicPostRes{
        val now = Instant.now();

        val post = getPostByIdOrElseThrow(id)
        val updatePost = musicPostMapper.toEntity(post, req, now)
        val saved = musicPostRepo.save(updatePost)
        return musicPostMapper.toRes(saved)
    }

    fun deletePost(id: Long){
        existPostByIdOrThrow(id);
        musicPostRepo.deleteById(id);
    }

    fun getAllPosts(): List<MusicPostRes> {
        return musicPostRepo.findAll()
            .map(musicPostMapper::toRes)
    }

    /**
     * MusicPost 조회, 없을 시 No Such element Exception 예외 처리
     */
    private fun getPostByIdOrElseThrow(id:Long) : MusicPost{
        return musicPostRepo.findById(id)
            .orElseThrow{
                NoSuchElementException("MusicPost Not found with id : $id")
            }
    }

    /**
     * MusicPost 존재 조회, 없을 시 No Such element Exception 예외 처리
     */
    private fun existPostByIdOrThrow(id:Long) {
        if (!musicPostRepo.existsById(id)){
            throw            NoSuchElementException("MusicPost Not found with id : $id")
        }
    }
}