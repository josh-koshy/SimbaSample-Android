package dev.koshy.simbasample;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("o/token/")
    Call<AuthToken> authenticate(
            @Header("Authorization") String authorization,
            @Header("Content-Type") String contentType,
            @Field("grant_type") String grantType
    );

    @GET("v2/apps/SCTest/contract/SCTest1/getFileCount/")
    Call<String> getFileCount(@Header("Authorization") String authorization);
}
