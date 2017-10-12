package service;

import model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import util.ApiUtil;

/**
 * Created by Fael on 12/10/2017.
 */

public interface RequestService {

    @GET(ApiUtil.base_url)
    Call<Movie> movieGet(
            @Query("t") String title,
            @Query("y") int year
    );



}
