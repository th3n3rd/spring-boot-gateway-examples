package com.example.connect.downstream

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.*

@RestController
class GetMovieDetailsController(
    private val movieCatalogue: MovieCatalogue
) {

    @Operation(summary = "Get movie details", operationId = "movieDetails")
    @GetMapping("/movies/{title}")
    fun handle(@PathVariable title: String): Response {
        return movieCatalogue.findByTitle(title)
            ?.let { Response(it.title, it.year.toString()) }
            ?: throw MovieNotFound(title)
    }

    @ExceptionHandler(MovieNotFound::class)
    @ResponseStatus(NOT_FOUND)
    fun handleMovieNotFound() {}

    @Schema(name = "MovieDetailsResponse")
    data class Response(val title: String, val year: String)
}
