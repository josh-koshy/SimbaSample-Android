package dev.koshy.simbasample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    // Create an OkHttp client and add the logging interceptor
    static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://simba-sbx-api.blocks.simbachain.com/";

    public static Retrofit getInstance() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
