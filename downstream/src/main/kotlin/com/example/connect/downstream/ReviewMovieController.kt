package com.example.connect.downstream

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
class ReviewMovieController {

    @Operation(summary = "Review movie", operationId = "reviewMovie")
    @PostMapping("/movies/{title}/reviews")
    @ResponseStatus(CREATED)
    fun handle(
        @PathVariable title: String,
        @RequestHeader(name = "X-Stub-Response", required = false) stubResponse: StubResponse?
    ) {
        return when(stubResponse) {
            StubResponse.ReviewSubmitted -> Unit
            StubResponse.ReviewAlreadySubmitted -> throw ReviewAlreadySubmitted(title)
            else -> TODO()
        }
    }

    @ExceptionHandler(ReviewAlreadySubmitted::class)
    @ResponseStatus(BAD_REQUEST)
    fun handleMovieNotFound(): Any {
        return Error("Review already submitted")
    }

    data class Error(val description: String)

    enum class StubResponse {
        ReviewSubmitted,
        ReviewAlreadySubmitted,
    }
}
