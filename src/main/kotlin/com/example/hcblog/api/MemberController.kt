package com.example.hcblog.api

import com.example.hcblog.domain.member.MemberSignUpRequest
import com.example.hcblog.service.MemberService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {
    private val logger = KotlinLogging.logger {}

//    @GetMapping("/members")
//    fun findAll() = memberService.findAll()

    @PostMapping("/members")
    fun signUp(@RequestBody request: MemberSignUpRequest) {
        logger.info { "signUp $request" }
        memberService.signUp(request)
    }
}
