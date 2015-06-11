package com.myhome.jdk5.gestion_exception;



public class SaisieErroneeException extends Exception {
    public SaisieErroneeException() {
        super();
    }
    public SaisieErroneeException(String s) {
        super(s);
    }
}