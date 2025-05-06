package com.br.core.enums;

public enum EnumCode {
    EV000("Event find by id not found"),
    GU000("Guest find by id not found");


    private String message;

    EnumCode(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
