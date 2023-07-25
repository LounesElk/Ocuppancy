package com.itso.occupancy.occupancy.config.errorhandler.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itso.occupancy.occupancy.dto.ErrorPublicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime   errorDate;
    private HttpStatus      status;
    private int             errorCode;
    private String          message;

    public ErrorPublicDto parseApiErrorToView(){
        return new ErrorPublicDto(
                this.getErrorCode(),
                this.getErrorDate(),
                this.getMessage());
    }
}