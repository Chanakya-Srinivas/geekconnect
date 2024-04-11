package com.studentassist.geekconnect.Response;

import com.studentassist.geekconnect.model.User;
import com.studentassist.geekconnect.model.UserResponseModel;
import org.springframework.http.HttpStatus;

public class UserResponse {

    private HttpStatus status;
    private String message;
    private UserResponseModel user;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = new UserResponseModel(user);
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public UserResponseModel getUser() {
        return user;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
