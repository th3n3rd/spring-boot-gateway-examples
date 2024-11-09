package com.example.connect.downstream

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(
	info = Info(
		title = "Downstream API",
		version = "v0.0.1"
	),
	servers = [
		Server(url = "http://localhost:8080", description = "Local server")
	]
)
class DownstreamApplication

fun main(args: Array<String>) {
	runApplication<DownstreamApplication>(*args)
}
