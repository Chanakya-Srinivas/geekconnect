package com.studentassist.geekconnect.Response;

import com.studentassist.geekconnect.responsemodel.UserResponseModel;
import org.springframework.http.HttpStatus;

public class UserResponse {

    private HttpStatus status;
    private String message;
    private Object object;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setObject(Object obj) {
        this.object = obj;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
