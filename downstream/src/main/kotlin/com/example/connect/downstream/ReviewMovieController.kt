package com.example.connect.downstream

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewMovieController {

    @Operation(summary = "Review movie", operationId = "reviewMovie")
    @PostMapping("/movies/{title}/reviews", headers = ["X-Stub-Response=review-submitted"])
    @ResponseStatus(CREATED)
    fun reviewSubmitted(@PathVariable title: String) {}

    @Operation(hidden = true)
    @PostMapping("/movies/{title}/reviews", headers = ["X-Stub-Response=review-already-submitted"])
    @ResponseStatus(BAD_REQUEST)
    fun reviewAlreadySubmitted(@PathVariable title: String): Any {
        return Error("Review already submitted")
    }

    data class Error(val description: String)
}