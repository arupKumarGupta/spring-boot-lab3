package com.springtraining.themovie.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class MovieResponse implements Serializable {

    @JsonAlias("Title")
    String title;

    @JsonAlias("Plot")
    String plot;

    @JsonAlias("Error")
    String error;

}
