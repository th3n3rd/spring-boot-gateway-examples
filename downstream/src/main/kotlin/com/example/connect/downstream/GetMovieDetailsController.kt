package com.example.connect.downstream

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.Year

@RestController
class GetMovieDetailsController(
    private val movieCatalogue: MovieCatalogue
) {

    @GetMapping("/movies/{title}")
    fun handle(@PathVariable title: String): Any {
        return movieCatalogue.findByTitle(title)
            ?.let { Response(it.title, it.year) }
            ?: TODO()
    }

    data class Response(val title: String, val year: Year)
}