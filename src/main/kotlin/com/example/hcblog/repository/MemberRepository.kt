package com.example.hcblog.repository

import com.example.hcblog.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository:JpaRepository<Member, Long> {
}