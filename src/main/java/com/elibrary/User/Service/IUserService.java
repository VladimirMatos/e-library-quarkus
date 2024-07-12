package com.elibrary.User.Service;

import com.elibrary.User.Dto.UpdateUserDto;
import com.elibrary.User.Dto.UserResponseDto;
import com.elibrary.User.Model.UserModel;
import com.elibrary.utils.PaginationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;


@ApplicationScoped
public interface IUserService {
    PaginationResponseDto<UserResponseDto> getAllUsers(int page, int totalResult);
    UserResponseDto getOneUser(ObjectId id);
    UserResponseDto updateOneUser(ObjectId id, UpdateUserDto user );
    boolean deleteOneUser(ObjectId id);
}
