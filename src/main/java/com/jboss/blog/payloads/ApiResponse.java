package com.jboss.blog.payloads;

public class ApiResponse {

    private String message;
    private Boolean error;
    private Object data;

    public ApiResponse(String message, Boolean error, Object data){
        this.message = message;
        this.error = error;
        this.data = data;
    }
}
