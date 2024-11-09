package com.example.connect.downstream

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.Year

@WebMvcTest(
    ListMoviesController::class,
    InMemoryMovieCatalogue::class
)
class ListMoviesApiTests {

    @Autowired
    private lateinit var client: MockMvc

    @Autowired
    private lateinit var movieCatalogue: InMemoryMovieCatalogue

    @Test
    fun `lists all movies successfully`() {
        givenTheCatalogueContains(
            Movie("first-movie", Year.of(1980)),
            Movie("second-movie", Year.of(2011)),
            Movie("third-movie", Year.of(2022)),
        )

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

    private fun givenTheCatalogueContains(vararg movie: Movie) {
        movie.forEach { movieCatalogue.add(it) }
    }
}

