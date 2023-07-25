package com.itso.occupancy.occupancy.config.errorhandler.factory;

import com.itso.occupancy.occupancy.config.errorhandler.bean.ApiError;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    private ResponseEntityBuilder() {
    }

    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError.parseApiErrorToView(), apiError.getStatus());
    }
}