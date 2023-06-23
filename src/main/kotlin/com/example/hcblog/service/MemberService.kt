package com.example.hcblog.service

import com.example.hcblog.domain.member.Member
import com.example.hcblog.domain.member.MemberSignUpRequest
import com.example.hcblog.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun findAll(): List<Member> = memberRepository.findAll()

    fun signUp(request: MemberSignUpRequest): Member {
        val member = request.toEntity()
        return memberRepository.save(member)
    }
}