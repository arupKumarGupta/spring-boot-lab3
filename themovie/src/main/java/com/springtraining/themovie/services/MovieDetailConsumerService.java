package com.springtraining.themovie.services;

import com.springtraining.themovie.models.MovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MovieDetailConsumerService {

    @Autowired
    private List<MovieResponse> movieDetails;

    @RabbitListener(queues = {"${exchange.queues.detail}","${exchange.queues.error}"})
    public void consumeMovieDetail(MovieResponse movieDetail) {
        log.info("movie detail: {}", movieDetail);
        movieDetails.add(movieDetail);
        log.info("Update cache with fetched movie detail for title: {}", movieDetail.getTitle());
    }

}
