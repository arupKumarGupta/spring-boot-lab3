package com.springtraining.themovie.controllers;

import com.springtraining.themovie.models.MovieResponse;
import com.springtraining.themovie.services.MoviePublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    List<MovieResponse> movieDetails;

    @Autowired
    private MoviePublisherService moviePublisherService;

    @GetMapping("/")
    public HttpEntity<List<MovieResponse>> getQueriedMovies() {
        return new ResponseEntity<>(movieDetails, HttpStatus.OK);
    }

    @PostMapping("/{movie}")
    public HttpEntity<Void> queryMovie(@PathVariable String movie) {
        moviePublisherService.publishMovie(movie);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
