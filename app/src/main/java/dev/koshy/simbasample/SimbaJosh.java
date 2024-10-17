package dev.koshy.simbasample;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SimbaJosh {
    private String clientId;
    private String clientSecret;
    private ApiService apiService;
    private AuthToken token;

    public SimbaJosh(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    private String getBasicAuthenticationHeader() {
        String valueToEncode = clientId + ":" + clientSecret;
        return "Basic " + android.util.Base64.encodeToString(valueToEncode.getBytes(), android.util.Base64.NO_WRAP);
    }

    public void authenticate(Callback<AuthToken> callback) {
        String authorizationHeader = getBasicAuthenticationHeader();
        String grantType = "grant_type=client_credentials";

        Call<AuthToken> call = apiService.authenticate(authorizationHeader, "application/x-www-form-urlencoded", grantType);
        call.enqueue(callback);
    }

    public void sendApiRequest(Callback<String> callback) {
        Log.d("SimbaJosh", "sendApiRequest Called");
        if (token == null || token.access_token.isEmpty()) {
            authenticate(new Callback<AuthToken>() {
                @Override
                public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                    Log.d("SimbaJosh", "onResponse received for sendApiRequest");
                    if (response.isSuccessful()) {
                        token = response.body();
                        Log.d("SimbaJosh", "response.isSuccessful() received for sendApiRequest");
                        makeApiCall(callback);
                    } else {
                        Log.d("SimbaJosh", "Authentication failed: " + response.message() + " " + response.code());
                        System.out.println("Authentication failed: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<AuthToken> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            makeApiCall(callback);
        }
    }

    private void makeApiCall(Callback<String> callback) {
        Log.d("SimbaJosh", "makeApiCall Called");
        String authorizationHeader = "Bearer " + token.access_token;
        Call<String> call = apiService.getFileCount(authorizationHeader);
        call.enqueue(callback);
    }
}
