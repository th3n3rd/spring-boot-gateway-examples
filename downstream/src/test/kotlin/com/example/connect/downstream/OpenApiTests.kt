package com.example.connect.downstream

import com.oneeyedmen.okeydoke.Approver
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject

@ExtendWith(JsonApprovalTesting::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class OpenApiTests {

    @Autowired
    private lateinit var client: TestRestTemplate

    @Test
    fun `generates open-api docs`(approver: Approver) {
        val result = client.getForObject<String>("/docs/openapi.json")

        approver.assertApproved(result?.toJsonPrettyString())
    }
}