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
        server.upsert(
            openAPIExpectation(
                Files.readString(
                    Path.of(
                        ResourceUtils.getURL("classpath:downstream-movies-api.json").toURI()
                    )
                )
            )
        )
    }

    fun listMovies(): ResponseEntity<String> {
        return client.getForEntity<String>("$baseUrl/movies")
    }
}