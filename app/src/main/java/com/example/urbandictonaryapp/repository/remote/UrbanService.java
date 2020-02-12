package com.example.urbandictonaryapp.repository.remote;

import com.example.urbandictonaryapp.model.UrbanResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface UrbanService {

    @Headers({
            "x-rapidapi-key: 9f37235fd8mshb6dda63ad1e1123p19e8e3jsnaa40752fe294",
            "x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com"
    })

    @GET("/define")
    Observable<UrbanResponse> getDefinitions(
            @Query("term") String term
    );

    @GET("/define")
    Single<UrbanResponse> getDefinitionsSingle(
            @Query("term") String term
    );


}
