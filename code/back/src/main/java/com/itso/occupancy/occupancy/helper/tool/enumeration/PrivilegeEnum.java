package com.itso.occupancy.occupancy.helper.tool.enumeration;


public enum PrivilegeEnum {

    USER_MENU_VIEW  (1L,"user_menu_view","Visualiser le menu utilisateur"),
    USERS_VIEW      (2L,"users_management_view","Visualiser la liste des utilisateurs"),
    USERS_MANAGE    (3L,"users_management_view_manage","Agir sur la liste des utilisateurs")
    ;

    /* Attributes */
    private final Long id;
    private final String name;
    private final String description;

    /*Constructor */
    PrivilegeEnum(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /*Getters*/
    public Long getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}
}
