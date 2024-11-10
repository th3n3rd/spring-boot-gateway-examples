package com.example.connect.downstream

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server

@OpenAPIDefinition(
    info = Info(
        title = "Downstream API",
        version = "v0.0.1"
    ),
    servers = [
        Server(url = "http://localhost:8080", description = "Local server")
    ]
)
object DownstreamApi {
    object Response {
        data class Error(val description: String)
    }
}