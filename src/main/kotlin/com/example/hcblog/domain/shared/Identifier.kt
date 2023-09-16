package com.example.hcblog.domain.shared

class Identifier(
    val value: String
) {
    init {
        val isAlphaNumeric = Regex("^[a-zA-Z0-9]*$")
        require(value.length in 1..10)
        require(isAlphaNumeric.matches(value))
    }

}