package com.example.hcblog.service

import com.example.hcblog.domain.member.Member
import com.example.hcblog.domain.member.MemberSignInRequest
import com.example.hcblog.domain.member.UserDetailsImpl
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    val authenticationManager: AuthenticationManager
) {
    fun authenticate(memberSignInRequest: MemberSignInRequest) {

        val authentication : Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                memberSignInRequest.email
                ,memberSignInRequest.password
            )
        )


        SecurityContextHolder.getContext().authentication = authentication

    }
}
