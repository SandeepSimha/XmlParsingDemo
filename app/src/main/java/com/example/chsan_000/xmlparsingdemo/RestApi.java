package com.example.chsan_000.xmlparsingdemo;

import com.example.chsan_000.pojo.MyResponse;
import com.example.chsan_000.pojo.Postalcodes;
import com.example.chsan_000.pojo.Timezone;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;


//add retrofit 1.9.0 jar in lib folder

//retrofit by default take JSON formate so we need to add one more library simple-xml-2.7.1 jar file in Lib folder
public class RestApi {
    String BASE_URL = "http://api.geonames.org/";


    public RestService getService() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL).build();

        return adapter.create(RestService.class);
    }

    public interface RestService {
        //
        @GET("/postalCodeLookupJSON")
        //void getWeather(@Query("q") String q, @Query("mode") String mode,@Query("APPID") String APPID, Callback<Response> callback);
        void getPostalCode(@Query("postalcode") String postalCode, @Query("username") String userName, Callback<MyResponse> callback);

        @GET("/postalCodeLookupJSON")
        void getPostalCodeByPlace(@Query("placename") String postalCode, @Query("username") String userName, Callback<MyResponse> callback);

        @GET("/timezoneJSON")
        void getTimezone(@Query("lng") double langitude,
                         @Query("lat") double lattitude,
                         @Query("username") String userName, Callback<Timezone> callback);

    }
}