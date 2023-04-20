package com.jazztech.apicadastro.presentation.handler;

import java.time.LocalDateTime;

public class ErrorHandler{
    private String title;
    private int status;
    private String detail;
    private String instance;
    private LocalDateTime timeStamp;

    public ErrorHandler(String title, int status, String detail, String instance, LocalDateTime timeStamp) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getDetails() {
        return detail;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }


}

