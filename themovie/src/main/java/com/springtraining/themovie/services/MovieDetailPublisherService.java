package com.springtraining.themovie.services;

import com.springtraining.themovie.models.MovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovieDetailPublisherService {

    @Value("${exchange.name}")
    private String exchange;

    @Value("${exchange.routingKeys.detail}")
    private String detailKey;

    @Value("${exchange.routingKeys.error}")
    private String errorKey;

    @Autowired
    private MovieLookupService movieLookupService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${exchange.queues.input}")
    public void publishMovieDetails(String movie) {
        MovieResponse movieResponse = movieLookupService.lookupMovie(movie);
        String routingKey = detailKey;
        if(movieResponse.getError() != null) {
            routingKey =  errorKey;
            movieResponse.setError(String.format("Movie %s not found", movie));
        }
        rabbitTemplate.convertAndSend(exchange, routingKey, movieResponse);
        log.info("Sent movie detail to : {} routing key", routingKey);
    }

}
