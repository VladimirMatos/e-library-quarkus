package com.elibrary.User.Resource;


import com.elibrary.User.Dto.UpdateUserDto;
import com.elibrary.User.Dto.UserResponseDto;
import com.elibrary.User.Service.UserService;
import com.elibrary.utils.PaginationResponseDto;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.bson.types.ObjectId;



import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
@Produces(APPLICATION_JSON)
public class UserResource {

    UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response getAllUser(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("totalResult") @DefaultValue("10") int totalResult){
        PaginationResponseDto<UserResponseDto> users = userService.getAllUsers(page, totalResult);

        return Response.status(Status.OK).entity(users).build();
    }

    @GET
    @Path("/{id}")
    public Response getOneUser(@PathParam("id") String id){
        UserResponseDto user = userService.getOneUser(new ObjectId(id));

        if (user == null) {
            return Response.status(Status.NO_CONTENT).build();
        }
        System.out.println(user);
        return Response.status(Status.OK).entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOneUser(@PathParam("id") String id){
        boolean userDeleted = userService.deleteOneUser(new ObjectId(id));

        if(!userDeleted){
            return  Response.notModified().entity(false).build();
        }

        return Response.ok().entity(true).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, @Valid UpdateUserDto user){
        System.out.println("User " + user);
        UserResponseDto userUpdated = userService.updateOneUser(new ObjectId(id), user);

        return Response.ok().entity(userUpdated).build();
    }

}
