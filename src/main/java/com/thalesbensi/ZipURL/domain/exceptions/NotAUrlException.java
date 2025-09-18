package com.thalesbensi.ZipURL.domain.exceptions;

public class NotAUrlException extends RuntimeException {

    public NotAUrlException(){super("That's not URL");}

    public NotAUrlException(String message){super(message);}
}
