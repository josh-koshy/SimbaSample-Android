package dev.koshy.simbasample;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("v2/apps/SCTest/contract/SCTest1/getFileCount/")
    Call<ResponseBody> getFileCount();
}
