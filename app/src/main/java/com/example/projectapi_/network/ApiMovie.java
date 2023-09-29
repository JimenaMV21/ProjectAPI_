package com.example.projectapi_.network;

import com.example.projectapi_.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiMovie { //Describir operaciones de la API que le pasemos

    @GET("movies/list.php") //Metodo de envio que usa http
    Call<List<Movie>> getMovies();

}
