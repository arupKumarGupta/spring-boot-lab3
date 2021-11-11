package com.springtraining.themovie;

import com.springtraining.themovie.models.MovieResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MovieConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public List<MovieResponse> movieDetails() {
        return new ArrayList<>();
    }
}
