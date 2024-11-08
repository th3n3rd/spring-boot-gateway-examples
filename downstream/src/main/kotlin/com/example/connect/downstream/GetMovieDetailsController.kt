package com.example.connect.downstream

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.*
import java.time.Year

@RestController
class GetMovieDetailsController(
    private val movieCatalogue: MovieCatalogue
) {

    @GetMapping("/movies/{title}")
    fun handle(@PathVariable title: String): Any {
        return movieCatalogue.findByTitle(title)
            ?.let { Response(it.title, it.year) }
            ?: throw MovieNotFound(title)
    }

    @ExceptionHandler(MovieNotFound::class)
    @ResponseStatus(NOT_FOUND)
    fun handleMovieNotFound() {}

    data class Response(val title: String, val year: Year)
}
