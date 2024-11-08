package com.example.connect.downstream

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FindMoviesController(
    private val movieCatalogue: MovieCatalogue
) {

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