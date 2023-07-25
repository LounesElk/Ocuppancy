package com.itso.occupancy.occupancy.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public abstract class Auditable {

    @CreatedBy
    private String	createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private String	lastModifiedBy;

    @LastModifiedDate
    private Date	lastModifiedDate;
}
