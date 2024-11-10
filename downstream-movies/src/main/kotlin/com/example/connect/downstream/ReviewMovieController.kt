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
        @RequestHeader(name = "X-Stub-Scenario", required = false) stubScenario: StubScenario?
    ) {
        return when(stubScenario) {
            StubScenario.ReviewSubmitted -> Unit
            StubScenario.ReviewAlreadySubmitted -> throw ReviewAlreadySubmitted(title)
            else -> TODO()
        }
    }

    @ExceptionHandler(ReviewAlreadySubmitted::class)
    @ResponseStatus(BAD_REQUEST)
    fun handleReviewAlreadySubmitted(): DownstreamMoviesApi.Response.Error {
        return DownstreamMoviesApi.Response.Error("Review already submitted")
    }

    enum class StubScenario {
        ReviewSubmitted,
        ReviewAlreadySubmitted,
    }
}
