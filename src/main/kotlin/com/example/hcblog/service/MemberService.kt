package com.example.hcblog.service

import com.example.hcblog.domain.member.Member
import com.example.hcblog.domain.member.MemberSignUpRequest
import com.example.hcblog.repository.MemberRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun findAll(): List<Member> = memberRepository.findAll()

    fun signUp(request: MemberSignUpRequest): Member {
        val member = request.toEntity()
        member.password = encryptPassword(member.password)
        return memberRepository.save(member)
    }

    fun loadMember(email : String) : Member? {
        return memberRepository.findByEmail(email)
    }

    private fun encryptPassword(password: String): String {
        return bCryptPasswordEncoder.encode(password)
    }
}
