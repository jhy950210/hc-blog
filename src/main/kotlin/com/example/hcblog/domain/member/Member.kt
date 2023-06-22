package com.example.hcblog.domain.member

import com.example.hcblog.domain.AuditingEntity
import com.example.hcblog.domain.post.Post
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    var name: String,
    var email: String,
    var password: String,

    @Enumerated(EnumType.STRING)
    var role: Role,
): AuditingEntity() {
}

enum class Role {
    ADMIN, USER
}
