package com.itso.occupancy.occupancy.config.errorhandler.customexception;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class UserNotAuthorizeException extends RuntimeException{

    @Getter
    private final List<String> msgList;

    /**
     * constructor
     * @param message the given message to send
     */
    public UserNotAuthorizeException(String message) {
        super(message);
        this.msgList = new ArrayList<>();
    }
}
