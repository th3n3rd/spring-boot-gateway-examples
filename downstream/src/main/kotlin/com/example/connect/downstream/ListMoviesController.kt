package com.example.connect.downstream

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListMoviesController(
    private val movieCatalogue: MovieCatalogue
) {

    @Operation(summary = "List movies", operationId = "listMovies")
    @GetMapping("/movies")
    fun handle(): Any {
        return Response(
            movieCatalogue.findAll().map {
                Response.Movie(it.title)
            }
        )
    }

    data class Response(val movies: List<Movie>) {
        data class Movie(val title: String)
    }
}