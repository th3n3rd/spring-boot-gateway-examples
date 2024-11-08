package com.example.connect.downstream

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(FindMoviesController::class)
class FindMoviesApiTests {

    @Autowired
    private lateinit var client: MockMvc

    @Test
    fun `lists all movies successfully`() {
        val result = client.get("/movies")

        result.andExpect {
            status { isOk() }
            content {
                contentType(APPLICATION_JSON)
                json("""
                {
                    "movies": [
                        { title: "first-movie" },
                        { title: "second-movie" },
                        { title: "third-movie" }
                    ]
                }
                """.trimIndent())
            }
        }
    }
}