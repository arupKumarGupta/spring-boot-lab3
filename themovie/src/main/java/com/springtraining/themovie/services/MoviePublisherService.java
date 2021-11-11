package com.springtraining.themovie.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MoviePublisherService {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${exchange.name}")
    private String exchange;

    @Value("${exchange.routingKeys.input}")
    private String key;

    public void publishMovie(String movie) {
        if(movie.length() > 0) {
            rabbitTemplate.convertAndSend(exchange,key, movie);
            log.info("Published {} to {} with routing key {}", movie, exchange, key);
        }
    }

}
