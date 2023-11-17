package com.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int iduser;
    private String email;
    private Date datecreated;
    private Boolean status;
    private Boolean role;
    private Double balance;
    
}
