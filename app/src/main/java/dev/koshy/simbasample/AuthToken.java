package dev.koshy.simbasample;

public class AuthToken {
    public String access_token;
    public int expires_in;
    public String token_type;
    public String scope;

    public AuthToken() {
        access_token = "";
    }

    @Override
    public String toString() {
        return access_token;
    }
}
