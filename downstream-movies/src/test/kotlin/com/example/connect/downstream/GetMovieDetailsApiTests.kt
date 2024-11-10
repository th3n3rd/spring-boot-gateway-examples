package com.example.connect.downstream

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.Year

@WebMvcTest(
    GetMovieDetailsController::class,
    InMemoryMovieCatalogue::class
)
class GetMovieDetailsApiTests {

    @Autowired
    private lateinit var client: MockMvc

    @Autowired
    private lateinit var movieCatalogue: InMemoryMovieCatalogue

    @Test
    fun `provides details for a given movie`() {
        givenTheCatalogueContains(Movie("any-movie", Year.of(1990)))

        val result = client.get("/movies/any-movie")

        result.andExpect {
            status { isOk() }
            content {
                contentType(APPLICATION_JSON)
                json("""
                {
                    "title": "any-movie",
                    "year": "1990"
                }
                """.trimIndent())
            }
        }
    }

    @Test
    fun `fails when the movie is not found`() {
        val result = client.get("/movies/unknown-movie")

        result.andExpect {
            status { isNotFound() }
        }
    }

    private fun givenTheCatalogueContains(vararg movie: Movie) {
        movie.forEach { movieCatalogue.add(it) }
    }
}
