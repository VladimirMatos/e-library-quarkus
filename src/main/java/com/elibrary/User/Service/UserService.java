package com.elibrary.User.Service;

import com.elibrary.User.Dto.UpdateUserDto;
import com.elibrary.User.Dto.UserResponseDto;
import com.elibrary.User.Model.UserModel;
import com.elibrary.User.Repository.UserRepository;
import com.elibrary.utils.MapperSource;
import com.elibrary.utils.PaginationResponse;
import com.elibrary.utils.PaginationResponseDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import org.bson.types.ObjectId;


@ApplicationScoped
public class UserService implements IUserService{

    UserRepository userRepository;
    MapperSource mapperSource;


    public UserService() {
    }

    @Inject
    public UserService(UserRepository userRepository, MapperSource mapperSource) {
        this.userRepository = userRepository;
        this.mapperSource = mapperSource;

    }


    @Override
    public PaginationResponseDto<UserResponseDto> getAllUsers(int page, int totalResult) {

       PaginationResponse<UserModel> users = userRepository.findAllWithPagination(page,totalResult);

        return mapperSource.mapPagination(users, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getOneUser(ObjectId id) {
        try {

            UserModel user = userRepository.findById(id);

            if(user == null){
                throw new BadRequestException("User not found");
            }

            return mapperSource.mapObject(user, UserResponseDto.class);

        }catch (Exception ex){
            throw new BadRequestException(ex);
        }
    }

    @Override
    public UserResponseDto updateOneUser(ObjectId id, UpdateUserDto user) {
        try{
            System.out.println(user);
            UserModel userFind = userRepository.findById(id);

            if(userFind == null){
                throw new BadRequestException("User not found");
            }

            userFind.name = user.name;
            userFind.lastname = user.lastname;
            userFind.email = user.email;
            userFind.updateDate();

            System.out.println(userFind);

            userRepository.update(userFind);

            return mapperSource.mapObject(userFind, UserResponseDto.class);

        }catch (Exception ignored){
            throw new BadRequestException();
        }

    }

    @Override
    public boolean deleteOneUser(ObjectId id) {
        try{
            getOneUser(id);
            return userRepository.deleteById(id);
        }catch (Exception ignored){

            return false;
        }
    }


}
