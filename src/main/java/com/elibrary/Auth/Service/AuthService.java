package com.elibrary.Auth.Service;

import com.elibrary.Auth.Dto.Request.User.CreateUserDto;
import com.elibrary.Auth.Dto.Request.User.LoginUserDto;
import com.elibrary.Auth.Dto.Response.ResponseDto;
import com.elibrary.User.Dto.UserResponseDto;
import com.elibrary.User.Model.UserModel;
import com.elibrary.User.Repository.UserRepository;
import com.elibrary.utils.Constants;
import com.elibrary.utils.MapperSource;
import com.elibrary.utils.PasswordEncode;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@ApplicationScoped
public class AuthService implements IAuthService{

    UserRepository userRepository;
    PasswordEncode passwordEncode;
    MapperSource mapperSource;

    @Inject
    public AuthService(UserRepository userRepository, PasswordEncode passwordEncode, MapperSource mapperSource) {
        this.userRepository = userRepository;
        this.passwordEncode = passwordEncode;
        this.mapperSource = mapperSource;
    }

    @Override
    public Response signup(CreateUserDto user) {
        try{

            ResponseDto responseDto = new ResponseDto();
            responseDto.message = Constants.USER_REGISTERED_SUCCESSFULLY;

            UserModel userFind = userRepository.findByEmail(user.email);

            if(userFind != null){
                return Response.status(Status.CONFLICT).entity(responseDto).build();
            }

            UserModel userCreate = new UserModel();

            userCreate.name = user.name;
            userCreate.lastname = user.lastname;
            userCreate.email = user.email;
            userCreate.password = passwordEncode.encode(user.password);
            userCreate.initDates();


            userRepository.persist(userCreate);

            return  Response.status(Status.CREATED).entity(userCreate).build();

        }catch (Exception ex){
            throw new BadRequestException(ex);
        }
    }

    @Override
    public UserResponseDto login(LoginUserDto user) {
        try {

            UserModel userFind = userRepository.findByEmail(user.email);

            if(!userFind.password.equals(passwordEncode.encode(user.password))){
                return  null;
            }

            return mapperSource.mapObject(userFind, UserResponseDto.class);
        }catch (Exception ignored){
            return null;
        }
    }


}
