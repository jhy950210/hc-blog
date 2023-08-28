package com.example.hcblog.service

import com.example.hcblog.domain.member.UserDetailsImpl
import com.example.hcblog.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return UserDetailsImpl(memberRepository.findByEmail(username))
    }
}
