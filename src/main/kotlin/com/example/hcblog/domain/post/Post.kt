package com.example.hcblog.domain.post

import com.example.hcblog.domain.AuditingEntity
import com.example.hcblog.domain.comment.Comment
import com.example.hcblog.domain.member.Member
import jakarta.persistence.*

@Entity
@Table(name = "post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content")
    var content: String,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member
): AuditingEntity() {
}