package dev.koshy.simbasample;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {
    @FormUrlEncoded
    @POST("o/token/")
    Call<AuthToken> authenticate(
            @Field("grant_type") String grantType
    );
}
