package com.damjan.librarymanagementsystem.exceptions;

public class BookNotFoundException extends LibraryException{
    public BookNotFoundException(String message) {
        super(message);
    }
}
