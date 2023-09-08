package com.example.hcblog.domain.member

import com.example.hcblog.domain.AuditingEntity
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
    companion object{
        fun create(id: Long, name: String, email: String, password: String, role: Role): Member {
            return Member(
                id, name, email, password, role
            )
        }
    }
}


enum class Role() {
    ADMIN
    , USER
    ;
}
