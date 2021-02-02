package com.skillbox.spring.springboot.springboot_rest.exeption_handling;

public class ApiError {

    private String info;

    public ApiError() {
    }

    public ApiError(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}