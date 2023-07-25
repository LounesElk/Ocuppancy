package com.itso.occupancy.occupancy.helper.tool.enumeration;


public enum RoleEnum {

    ADMIN               (1L,"Admin"),
    CLINIQUE            (2L,"Clinique"),
    SALES               (3L,"Commercial"),
    CUSTOMER_SERVICE    (4L,"Service Client")
    ;

    /* Attributes */
    private final Long id;
    private final String label;


    /*Constructor */
    RoleEnum(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    /*Getters*/
    public Long getId() {return id;}
    public String getLabel() {return label;}

}
