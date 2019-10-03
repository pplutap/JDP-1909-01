package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Email {

    private String mailTo;
    private String subject;
    private String message;

}
