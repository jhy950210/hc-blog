package com.example.hcblog.config

import com.example.hcblog.domain.member.UserDetailsImpl
import com.example.hcblog.service.CustomUserDetailsService
import com.example.hcblog.service.MemberService
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig(
    val customUserDetailsService: CustomUserDetailsService
) {

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(customUserDetailsService)
        authProvider.setPasswordEncoder(bCryptPasswordEncoder())
        return authProvider
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager? {
        return config.authenticationManager
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun webSecurityConfigurerAdapter(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring().requestMatchers("../**")
        }
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .headers{ it -> it.frameOptions { it.sameOrigin() }}
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/members","/login").permitAll()
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
            }
            .httpBasic { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
//            .sessionManagement { SessionCreationPolicy.STATELESS }
            .authenticationProvider(authenticationProvider())       // 괄호 중괄호 차이
            .logout {
                it.logoutUrl("member/logout")
                    .logoutSuccessHandler { request, response, authentication ->
                        SecurityContextHolder.clearContext()
                    }
            }
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        configuration.addAllowedOrigin("*")
        configuration.addAllowedHeader("*")
        configuration.addAllowedMethod("*")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }


}

