package com.qquique.jsbm.application.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private Date lastDateCreated;
    private Date lastDateModified;
}
