package com.elibrary.Auth.Dto.Request.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@ApplicationScoped
public class CreateUserDto implements Serializable {

    @NotBlank(message = "Name is required")
    public String name;


    @NotBlank(message = "Lastname is required")
    public String lastname;


    @NotBlank(message = "Email is required")
    @Email(message = "Email not valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    public String email;


    @NotBlank(message = "Password is required")
    public String password;
}
