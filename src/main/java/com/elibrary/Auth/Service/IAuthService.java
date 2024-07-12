package com.elibrary.Auth.Service;

import com.elibrary.Auth.Dto.Request.User.CreateUserDto;
import com.elibrary.User.Dto.UserResponseDto;
import com.elibrary.Auth.Dto.Request.User.LoginUserDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public interface IAuthService {
    Response signup(CreateUserDto user);
    UserResponseDto login(LoginUserDto user);
}
