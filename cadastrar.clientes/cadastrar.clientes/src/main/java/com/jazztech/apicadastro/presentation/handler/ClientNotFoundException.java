package com.jazztech.apicadastro.presentation.handler;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String msg){
        super(msg);
    }
}
