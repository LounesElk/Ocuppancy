package com.itso.occupancy.occupancy.config.errorhandler.customexception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class VersionException extends RuntimeException {


    @Getter
    private final List<String> msgList;

    /**
     * Constructor
     * @param message message to send
     */
    public VersionException(String message) {
        super(message);
        this.msgList = new ArrayList<>();
    }
}