package com.elibrary.Auth.Dto.Request.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@ApplicationScoped
public class LoginUserDto implements Serializable {

    @NotBlank(message = "Email is required")
    @Email(message = "Email not valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    public String email;


    @NotBlank(message = "Password is required")
    public String password;
}
