package dev.koshy.simbasample;

import com.google.gson.annotations.SerializedName;

public class AuthToken {
    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("expires_in")
    public int expiresIn;

    @SerializedName("token_type")
    public String tokenType;

    public String scope;
}
