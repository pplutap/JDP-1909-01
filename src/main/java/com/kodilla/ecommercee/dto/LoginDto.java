package com.kodilla.ecommercee.dto;

public class LoginDto {

    private Boolean loggedIn;
    private Long sessionId;

    public LoginDto() {
    }

    public LoginDto(Boolean loggedIn, Long sessionId) {
        this.loggedIn = loggedIn;
        this.sessionId = sessionId;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public Long getSessionId() {
        return sessionId;
    }

}
