package com.clinpride.SecurityPostgres.Config.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String error; // Add the error field

    // Constructors, getters, and setters

    // Builder pattern
    public static class Builder {
        private String token;
        private String error;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public AuthenticationResponse build() {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setToken(this.token);
            response.setError(this.error);
            return response;
        }
    }

    // Getters and setters for token and error
}