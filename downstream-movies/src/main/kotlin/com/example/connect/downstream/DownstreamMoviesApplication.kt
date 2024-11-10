package com.example.connect.downstream

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DownstreamMoviesApplication

fun main(args: Array<String>) {
    runApplication<DownstreamMoviesApplication>(*args)
}
