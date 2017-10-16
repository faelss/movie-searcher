package test.android.br.com.movie_searcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import model.Movie;

public class MovieProfile extends AppCompatActivity {

    private TextView movieText;
    private TextView movieTitle;
    private Glide glide;
    private Movie movie;
    private ImageView moviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        movie = (Movie) getIntent().getExtras().getSerializable("movie");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_profile);
        moviePoster = findViewById(R.id.poster);
        movieText = findViewById(R.id.movieText);
        movieTitle = findViewById(R.id.movieTitle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test",movie.getPoster());
        movieTitle.setText(movie.getTitle());
        Glide.with(this)
                .load(movie.getPoster())
                .into(moviePoster);
        movieText.setText(
            "Sinopse: \n\n" + movie.getPlot() + "\n\n Ano: " + movie.getYear() + "\n\n Gênero: " + movie.getGenre()
            + "\n\n Diretores: " + movie.getDirector() + "\n\n Elenco: " + movie.getActors() + "\n\n Produção: " + movie.getProduction()
            + "\n\n Site: " + movie.getWebsite()
        );

    }
}
