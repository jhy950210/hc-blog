package com.example.hcblog.domain

import com.example.hcblog.domain.shared.Identifier
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class IdentifierTest: FunSpec ({
    test("한글이 포함되면 안된다") {
        shouldThrow<IllegalArgumentException> {
            Identifier("한글aa")
        }
    }

    test("10글자 초과 안된다") {
        shouldThrow<IllegalArgumentException> {
            Identifier("012345678910")
        }
    }

    test("0글자 안된다") {
        shouldThrow<IllegalArgumentException> {
            Identifier("")
        }
    }
})