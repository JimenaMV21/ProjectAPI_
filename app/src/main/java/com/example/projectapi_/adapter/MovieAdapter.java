package com.example.projectapi_.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectapi_.R;
import com.example.projectapi_.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> { //Incorporar dentro de RecycleView el dieño y componentes de item_movie

    private List<Movie> movies; //Guardar el listado de películas
    private Context context; //Acceeder al contexto de la aplicacion

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false); //Inflar el archivo xml
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //Colocar los componentes de ItemMovie la imagen y el titulo
        if (movies != null && movies.size() > position) {
            holder.tv_titulo.setText(movies.get(position).getTitulo());
            Glide.with(context).load(movies.get(position).getPortada()).into(holder.iv_portada);
        }


    }

    @Override
    public int getItemCount() { //Espcifica la cantidad de items a devolver
        return movies != null ? movies.size() : 0; //Retornamos el tamaño de elementos que hay en la lista
        //NULL
    }

    public class ViewHolder extends RecyclerView.ViewHolder { //Especifica los componentes del archivo item_movies se usaran para mosotrar en el json
        private ImageView iv_portada;
        private TextView tv_titulo;
        public ViewHolder(@NonNull View itemView) { //Constructor y enlazamos
            super(itemView);
            iv_portada = itemView.findViewById(R.id.iv_portada);
            tv_titulo = itemView.findViewById(R.id.tv_titulo);
        }
    }

    public MovieAdapter(List<Movie> movies, Context applicationContext) {
        this.movies = movies;
        this.context = applicationContext;
    }
}
