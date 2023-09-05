package com.example.hcblog.config

import com.example.hcblog.domain.member.Role
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig(
    val authenticationProvider: AuthenticationProvider
) {
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
            .formLogin { it -> it.defaultSuccessUrl("/post")
                .loginProcessingUrl("/auth")
            }
            .headers{ it -> it.frameOptions { it.sameOrigin() }}    //h2 콘솔
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/members/**","/auth").permitAll()
                    .requestMatchers(PathRequest.toH2Console()).permitAll() //h2 콘솔
                    .requestMatchers("/post").hasRole(Role.ADMIN.toString())
                .anyRequest().authenticated()
            }
            .httpBasic { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
//            .sessionManagement { SessionCreationPolicy.STATELESS }
            .authenticationProvider(authenticationProvider)       // 괄호 중괄호 차이
            .logout {
                it.logoutUrl("/members/logout")
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

