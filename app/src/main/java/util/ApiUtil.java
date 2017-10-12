package util;

import retrofit.RetrofitClient;
import service.RequestService;

/**
 * Created by Fael on 12/10/2017.
 */

public class ApiUtil {

    public static final String base_url = "http://www.omdbapi.com/";
    public static final String api_key = "3a49d0b9";

    private ApiUtil(){
        super();
    }

    public static RequestService apiService(){

        return RetrofitClient.getClient(base_url).create(RequestService.class);

    }


}
