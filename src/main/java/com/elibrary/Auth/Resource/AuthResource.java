package com.elibrary.Auth.Resource;

import com.elibrary.Auth.Dto.Request.User.CreateUserDto;
import com.elibrary.User.Dto.UserResponseDto;
import com.elibrary.Auth.Dto.Request.User.LoginUserDto;
import com.elibrary.Auth.Service.AuthService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;


import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/Auth")
@Produces(APPLICATION_JSON)
public class AuthResource {

    AuthService authService;

    @Inject
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Path("/login")
    public Response login(@Valid LoginUserDto user){
        UserResponseDto loginUser = authService.login(user);

        if(loginUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok().entity(loginUser).build();
    }

    @POST
    @Path("/signup")
    public Response signup(@Valid CreateUserDto user){
        return  authService.signup(user);
    }


}
