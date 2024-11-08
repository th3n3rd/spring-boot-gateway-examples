package com.example.connect.downstream

import org.springframework.stereotype.Repository
import java.util.concurrent.CopyOnWriteArrayList

interface MovieCatalogue {
    fun findAll(): List<Movie>
}

@Repository
class InMemoryMovieCatalogue : MovieCatalogue {
    private val movies = CopyOnWriteArrayList<Movie>()

    fun add(movie: Movie) {
        movies.add(movie)
    }

    override fun findAll(): List<Movie> {
        return movies
    }
}