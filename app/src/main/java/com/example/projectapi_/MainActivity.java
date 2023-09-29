package com.example.projectapi_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.projectapi_.adapter.MovieAdapter;
import com.example.projectapi_.model.Movie;
import com.example.projectapi_.network.ApiClient;
import com.example.projectapi_.network.ApiMovie;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Debug", "OnCreate");

        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        Log.d("Debug", "RecyclerView");

        // No configure el adaptador aquí

        showMovies(); // Llama a la función para obtener los datos
    }

    public void showMovies(){
        Log.d("Debug", "ShowMovies");
        retrofit2.Call<List<Movie>> call = ApiClient.getClient().create(ApiMovie.class).getMovies();
        Log.d("Debug", "Retrofit");
        call.enqueue(new Callback<List<Movie>>() {


            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                Log.d("Debug", "OnResponse" + response.toString());
                if(response.isSuccessful()){
                    movies = response.body();
                    movieAdapter = new MovieAdapter(movies, getApplicationContext());
                    recyclerView.setAdapter(movieAdapter);
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(MainActivity.this, "Error in API response.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e("Debug", "onFailure: " + t.getMessage(), t);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (t instanceof IOException) {
                            Toast.makeText(MainActivity.this, "Network error. Please check your internet connection.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error in API response.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}



//Se usa Xamp para levantar la API
