package com.example.hcblog.repository

import com.example.hcblog.domain.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>{
}