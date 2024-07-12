package com.elibrary.User.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserDto implements Serializable {
    @NotBlank(message = "Name is required")
    public String name;


    @NotBlank(message = "Lastname is required")
    public String lastname;


    @NotBlank(message = "Email is required")
    @Email(message = "Email not valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    public String email;
}
