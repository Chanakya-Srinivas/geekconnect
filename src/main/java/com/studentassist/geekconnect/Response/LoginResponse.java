package com.studentassist.geekconnect.Response;

import com.mysql.cj.Session;
import com.studentassist.geekconnect.model.User;
import org.springframework.http.HttpStatus;

public class LoginResponse {

    private HttpStatus status;
    private String message;
    private String userName;
    private String id;
    private String sessionToken;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getMessage() {
        return message;
    }

    public String getUserName() {
        return userName;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getID() {
        return id;
    }

}
