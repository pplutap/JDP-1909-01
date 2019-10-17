package com.kodilla.ecommercee;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Email {

    private String mailTo;
    private String subject;
    private String message;

}
