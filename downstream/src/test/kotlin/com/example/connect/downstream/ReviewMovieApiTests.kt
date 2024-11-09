package com.example.connect.downstream

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(
    ReviewMovieController::class,
)
class ReviewMovieApiTests {

    @Autowired
    private lateinit var client: MockMvc

    @Test
    fun `review submitted successfully`() {
        val result = client.post("/movies/any-movie/reviews") {
            header("X-Stub-Scenario", "ReviewSubmitted")
            content = """{ "rating": 1 }""".trimIndent()
        }

        result.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `review already submitted`() {
        val result = client.post("/movies/any-movie/reviews") {
            header("X-Stub-Scenario", "ReviewAlreadySubmitted")
            content = """{ "rating": 5 }""".trimIndent()
        }

        result.andExpect {
            status { is4xxClientError() }
            content {
                contentType(APPLICATION_JSON)
                json("""
                {
                    "description": "Review already submitted"
                }
                """.trimIndent())
            }
        }
    }
}
