package br.com.ramos.nasaapi.dto;

public class AuthResponse {

    private String token;
    private String type = "Bearer";
    private String username;

    public AuthResponse() {
        this.token = token;
        this.username = username;
    }

    public String getToken() { return token; }
    public String getType() { return type; }
    public String getUsername() { return username; }

    public void setToken(String token) {
        this.token = token;
    }
}