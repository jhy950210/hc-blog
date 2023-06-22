package com.example.hcblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class HcBlogApplication

fun main(args: Array<String>) {
	runApplication<HcBlogApplication>(*args)
}
