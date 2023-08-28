package com.example.hcblog.service

import com.example.hcblog.domain.member.MemberSignInRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    val customUserDetailsService: CustomUserDetailsService
    ,val authenticationManager: AuthenticationManager
) {
    fun authenticate(memberSignInRequest: MemberSignInRequest) {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                memberSignInRequest.email
                ,memberSignInRequest.password
            )
        )

    }
}
