package com.jazztech.apicadastro.presentation.handler;

public class ClientNotCreatedException extends RuntimeException{
    public ClientNotCreatedException(String msg){
        super(msg);
    }
}
