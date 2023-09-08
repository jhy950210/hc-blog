package com.example.hcblog.domain.member

data class MemberSignUpRequest(
    val email: String,
    val password: String,
    val name: String,
    val role: Role
) {
    fun toEntity(): Member {
        return Member(
            email = email,
            password = password,
            name = name,
            role = role,
        )
    }
}
