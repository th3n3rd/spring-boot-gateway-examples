package com.example.connect.downstream

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewMovieController {

    @PostMapping("/movies/{title}/reviews", headers = ["X-Stub-Response=review-submitted"])
    @ResponseStatus(CREATED)
    fun reviewSubmitted(@PathVariable title: String) {}

    @PostMapping("/movies/{title}/reviews", headers = ["X-Stub-Response=review-already-submitted"])
    @ResponseStatus(BAD_REQUEST)
    fun reviewAlreadySubmitted(@PathVariable title: String): Any {
        return Error("Review already submitted")
    }

    data class Error(val description: String)
}