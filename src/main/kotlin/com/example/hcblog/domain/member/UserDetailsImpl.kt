package com.example.hcblog.domain.member

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val member: Member?) : UserDetails {
    private val enable = true
    override fun getAuthorities(): List<out GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_${member?.role?.name}"))
    }


    override fun getPassword(): String? = member?.password

    override fun getUsername(): String? = member?.email

    override fun isAccountNonExpired(): Boolean = enable

    override fun isAccountNonLocked(): Boolean = enable

    override fun isCredentialsNonExpired(): Boolean = enable

    override fun isEnabled(): Boolean = enable

}

