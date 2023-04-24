package com.jazztech.apicadastro.presentation.handler;

import java.time.LocalDateTime;

public record ErrorHandler(
    String title,
    int status,
    String detail,
    String instance,
    LocalDateTime timeStamp
){
}

