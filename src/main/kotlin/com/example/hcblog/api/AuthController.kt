package com.example.hcblog.api

import com.example.hcblog.domain.member.MemberSignInRequest
import com.example.hcblog.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/auth")
    fun login(@RequestBody memberSignInRequest: MemberSignInRequest) : ResponseEntity<Any> {
        authenticationService.authenticate(memberSignInRequest)
        return ResponseEntity.ok(SecurityContextHolder.getContext())
    }
}
