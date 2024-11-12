package com.example.connect.gateway

import org.mockserver.integration.ClientAndServer.startClientAndServer
import org.mockserver.mock.OpenAPIExpectation.openAPIExpectation
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.ResponseEntity
import org.springframework.util.ResourceUtils
import java.nio.file.Files
import java.nio.file.Path


class DownstreamMoviesApi(private val client: TestRestTemplate) {
    private val server = startClientAndServer()

    val baseUrl = "http://localhost:${server.port}"

    init {
        setupStubs()
    }

    fun listMovies(): ResponseEntity<String> {
        return client.getForEntity<String>("$baseUrl/movies")
    }

    fun movieDetails(title: String): ResponseEntity<String> {
        return client.getForEntity<String>("$baseUrl/movies/$title")
    }

    fun reviewMovie(title: String, rating: Int): ResponseEntity<String> {
        return client.postForEntity<String>("$baseUrl/movies/$title/reviews", mapOf("rating" to rating))
    }

    private fun setupStubs(overrideOperations: Map<String, String> = mapOf()) {
        server.upsert(
            openAPIExpectation(
                Files.readString(
                    Path.of(
                        ResourceUtils.getURL("classpath:downstream-movies-api.json").toURI()
                    )
                ),
                mapOf(
                    "listMovies" to "200",
                    "movieDetails" to "200",
                    "reviewMovie" to "201",
                ) + overrideOperations
            )
        )
    }
}