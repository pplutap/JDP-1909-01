package com.kodilla.ecommercee.dto;

public class UserDto {

    private Long id;
    private String username;
    private Integer status;
    private Long userKey;

    public UserDto() {
    }

    public UserDto(Long id, String username, Integer status, Long userKey) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserKey() {
        return userKey;
    }

}
