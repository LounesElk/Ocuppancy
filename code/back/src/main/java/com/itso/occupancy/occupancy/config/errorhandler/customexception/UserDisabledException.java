package com.itso.occupancy.occupancy.config.errorhandler.customexception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class UserDisabledException extends RuntimeException {


    @Getter
    private final List<String> msgList;

    /**
     * Constructor
     * @param message message to send
     */
    public UserDisabledException(String message) {
        super(message);
        this.msgList = new ArrayList<>();
    }
}