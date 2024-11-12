package com.example.connect.gateway

import org.mockserver.integration.ClientAndServer.startClientAndServer
import org.mockserver.mock.OpenAPIExpectation.openAPIExpectation
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
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
                    "movieDetails" to "200"
                ) + overrideOperations
            )
        )
    }
}