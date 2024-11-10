package com.example.connect.downstream

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListMoviesController(
    private val movieCatalogue: MovieCatalogue
) {

    @Operation(summary = "List movies", operationId = "listMovies")
    @GetMapping("/movies")
    fun handle(): Response {
        return Response(
            movies = movieCatalogue.findAll().map { it.title }
        )
    }

    @Schema(name = "ListMoviesResponse")
    data class Response(val movies: List<String>)
}