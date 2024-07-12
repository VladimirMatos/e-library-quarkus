package com.elibrary.utils;

import com.elibrary.User.Dto.UserResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class PaginationResponseDto<E> {
    public Integer page;
    public Integer totalPages;
    public List<E> data;

}
