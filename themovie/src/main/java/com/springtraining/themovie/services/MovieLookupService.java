package com.springtraining.themovie.services;

import com.springtraining.themovie.models.MovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class MovieLookupService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${omdb.search}")
    private String omdbSearchUrl;

    public MovieResponse lookupMovie(String movie) {
        ResponseEntity<MovieResponse> movieResponseResponseEntity = restTemplate.getForEntity(omdbSearchUrl+movie,MovieResponse.class);
        log.info("Successfully fetched data about {} from API", movie);
        return movieResponseResponseEntity.getBody();
    }

}
