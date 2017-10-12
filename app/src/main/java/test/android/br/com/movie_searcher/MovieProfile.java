package test.android.br.com.movie_searcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import model.Movie;

public class MovieProfile extends AppCompatActivity {

    private TextView movieText;
    private ImageView moviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_profile);
        moviePoster = findViewById(R.id.poster);
        movieText = findViewById(R.id.movieText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Movie movie = (Movie) getIntent().getExtras().getSerializable("movie");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(movie.getTitle());
        Glide.with(this)
                .load(movie.getPoster())
                .into(moviePoster);
        movieText.setTextColor(getColor(R.color.white));
        movieText.setText(
            "Sinopse: \n\n " + movie.getPlot() + "Ano: \n\n " + movie.getYear() + "Gênero: \n\n " + movie.getGenre()
            + "Diretores: \n\n " + movie.getDirector() + "Elenco: \n\n " + movie.getActors() + "Produção: \n\n " + movie.getProduction()
            + "Site: \n\n " + movie.getWebsite()
        );

    }
}
