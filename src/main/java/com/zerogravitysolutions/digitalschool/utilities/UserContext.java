package com.zerogravitysolutions.digitalschool.utilities;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    private String authToken;
    private Long userId;
    private String userEmail;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
