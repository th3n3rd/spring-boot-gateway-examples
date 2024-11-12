package com.example.connect.gateway

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource

@SpringBootTest(webEnvironment = RANDOM_PORT)
class MoviesApiTests {

    @Autowired
    private lateinit var client: TestRestTemplate

    @Test
    fun `lists movies`() {
        val response = client.getForEntity<String>("/api/v1/movies")

        assertThat(response.body).isEqualTo(downstreamMoviesApi.listMovies().body)
    }

    @Test
    fun `get movie details`() {
        val response = client.getForEntity<String>("/api/v1/movies/any-movie")

        assertThat(response.body).isEqualTo(downstreamMoviesApi.movieDetails("any-movie").body)
    }

    @Test
    fun `review movie`() {
        val response = client.postForEntity<String>("/api/v1/movies/any-movie", mapOf("rating" to 4))

        assertThat(response.body).isEqualTo(downstreamMoviesApi.reviewMovie("any-movie", 4).body)
    }

    companion object {
        private val downstreamMoviesApi = DownstreamMoviesApi(TestRestTemplate())

        @JvmStatic
        @DynamicPropertySource
        fun configureDownstreamServicesUri(registry: DynamicPropertyRegistry) {
            registry.add("services.downstream-movies.uri") { downstreamMoviesApi.baseUrl }
        }
    }
}
