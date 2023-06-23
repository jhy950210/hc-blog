package com.example.hcblog.service

import com.example.hcblog.HcBlogApplication
import com.example.hcblog.domain.member.MemberSignUpRequest
import com.example.hcblog.domain.member.Role
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [HcBlogApplication::class])
@Transactional
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
//    private val memberRepository: MemberRepository
): FunSpec({

    beforeTest {
        memberService.signUp(MemberSignUpRequest("test1", "test", "test", Role.USER))
    }

    test("findAll") {
        val members = memberService.findAll()
        members.size shouldBe 1
    }

    test("signUp") {
        val member = memberService.signUp(MemberSignUpRequest("test2", "test2", "test2", Role.USER))
        member.name shouldBe "test2"
    }
})
