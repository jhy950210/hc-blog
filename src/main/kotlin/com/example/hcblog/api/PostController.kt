package com.example.hcblog.api

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController {

    private val logger = KotlinLogging.logger {}
    @GetMapping("/post")
    fun getPost() : ResponseEntity<Any> {
        logger.info { "테스트" }
        return ResponseEntity.ok("success")
    }

}
