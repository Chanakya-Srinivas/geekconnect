package com.studentassist.geekconnect.Response;

import com.studentassist.geekconnect.model.User;
import org.springframework.http.HttpStatus;

public class LoginResponse {

    private HttpStatus status;
    private String message;
    private User user;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
