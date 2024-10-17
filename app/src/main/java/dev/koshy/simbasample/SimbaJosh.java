package dev.koshy.simbasample;

import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimbaJosh {
    private String clientId;
    private String clientSecret;
    private APIService apiService;
    private AuthService authService;
    private AuthToken token;

    public SimbaJosh(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        // Create the AuthService for authentication
        this.authService = RetrofitClient.getAuthInstance(clientId, clientSecret).create(AuthService.class);
        // APIService will be initialized after authentication
    }

    public void authenticate(Callback<AuthToken> callback) {
        String grantType = "client_credentials";
        Call<AuthToken> call = authService.authenticate(grantType);
        call.enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if (response.isSuccessful()) {
                    token = response.body();
                    RetrofitClient.setAccessToken(token.accessToken);
                    // Initialize APIService after setting the access token
                    apiService = RetrofitClient.getInstance().create(APIService.class);
                    callback.onResponse(call, response);
                } else {
                    Log.e("SimbaJosh", "Authentication failed: " + response.message());
                    callback.onFailure(call, new Exception("Authentication failed: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                Log.e("SimbaJosh", "Authentication error: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }

    public void sendApiRequest(Callback<ResponseBody> callback) {
        Log.d("SimbaJosh", "sendApiRequest Called");
        if (token == null || token.accessToken == null || token.accessToken.isEmpty()) {
            authenticate(new Callback<AuthToken>() {
                @Override
                public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                    if (response.isSuccessful()) {
                        makeApiCall(callback);
                    } else {
                        Log.e("SimbaJosh", "Authentication failed: " + response.message());
                        callback.onFailure(null, new Exception("Authentication failed: " + response.message()));
                    }
                }

                @Override
                public void onFailure(Call<AuthToken> call, Throwable t) {
                    Log.e("SimbaJosh", "Authentication error: " + t.getMessage());
                    callback.onFailure(null, t);
                }
            });
        } else {
            makeApiCall(callback);
        }
    }

    private void makeApiCall(Callback<ResponseBody> callback) {
        Log.d("SimbaJosh", "makeApiCall Called");
        Call<ResponseBody> call = apiService.getFileCount();
        call.enqueue(callback);
    }
}
