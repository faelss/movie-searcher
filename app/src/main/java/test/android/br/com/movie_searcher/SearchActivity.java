package test.android.br.com.movie_searcher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RequestService;
import util.ApiUtil;

public class SearchActivity extends AppCompatActivity {

    private RequestService request;
    private TextView welcomeText;
    private EditText movieName;
    private EditText movieYear;
    private Button sendButton;
    private Button resetButton;
    private Intent movieProfile;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        resetButton = findViewById(R.id.resetField);
        sendButton = findViewById(R.id.sendButton);
        movieName = findViewById(R.id.movieName);
        movieYear = findViewById(R.id.movieYear);
        request = ApiUtil.apiService();
        welcomeText = findViewById(R.id.welcomeText1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userName = getIntent().getStringExtra("username");
        if(userName == null){
            welcomeText.setText("Bem vindo sem nome, \n digite o nome do filme e o ano ou só o nome: ");
        }else{
            welcomeText.setText("Bem vindo " + userName + " digite o nome do filme e o ano ou só o nome: ");
        }
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieName.setText("");
                movieYear.setText("");
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movieName.getText().toString().matches("")){
                    new AlertDialog.Builder(SearchActivity.this)
                            .setTitle("ERRO")
                            .setMessage("Digite um nome do filme.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                }else if(movieYear.getText().toString().matches("")) {
                    sendRequest(String.valueOf(movieName.getText()), null);
                    Log.d("Test", "test");
                }else if(!movieYear.getText().toString().matches("") && !movieName.getText().toString().matches("")){
                    if(!movieYear.getText().toString().matches("^(19|20)\\d{2}$")) {
                        new AlertDialog.Builder(SearchActivity.this)
                                .setTitle("ERRO")
                                .setMessage("Data inválida")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).show();
                    }else{
                        sendRequest(String.valueOf(movieName.getText()),String.valueOf(movieYear.getText()));
                        Log.d("Test","test1");
                    }
                }
            }
        });
    }

    private void sendRequest(String movieName , String year){
        request.movieGet(movieName,year,ApiUtil.api_key).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.isSuccessful()){
                    movieProfile = new Intent(SearchActivity.this,MovieProfile.class);
                    Movie movie = new Movie(
                            response.body().getTitle(),
                            response.body().getYear(),
                            response.body().getRated(),
                            response.body().getReleased(),
                            response.body().getRuntime(),
                            response.body().getGenre(),
                            response.body().getDirector(),
                            response.body().getWriter(),
                            response.body().getActors(),
                            response.body().getPlot(),
                            response.body().getLanguage(),
                            response.body().getCountry(),
                            response.body().getAwards(),
                            response.body().getPoster(),
                            response.body().getRatings(),
                            response.body().getMetascore(),
                            response.body().getImdbRating(),
                            response.body().getImdbVotes(),
                            response.body().getImdbID(),
                            response.body().getType(),
                            response.body().getDVD(),
                            response.body().getBoxOffice(),
                            response.body().getProduction(),
                            response.body().getWebsite()
                    );
                    movieProfile.putExtra("movie", movie);
                    if(movieProfile != null){
                        startActivity(movieProfile);
                    }
                }else{
                    new AlertDialog.Builder(SearchActivity.this)
                            .setTitle("ERRO")
                            .setMessage("API respondeu com Response Code: " + response.code())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                new AlertDialog.Builder(SearchActivity.this)
                        .setTitle("ERRO")
                        .setMessage("Algo deu errado ao acessar a API")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            }
        });
    }


}
