package com.example.connect.downstream

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FindMoviesController {

    @GetMapping("/movies")
    fun handle(): Any {
        return Response(listOf(
            Response.Movie("first-movie"),
            Response.Movie("second-movie"),
            Response.Movie("third-movie"),
        ))
    }

    data class Response(val movies: List<Movie>) {
        data class Movie(val title: String)
    }
}