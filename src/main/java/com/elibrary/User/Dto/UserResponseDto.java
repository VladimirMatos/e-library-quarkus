package com.elibrary.User.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
public class UserResponseDto {
    @JsonProperty("_id")
    public ObjectId id;
    public String name;
    public String lastname;
    public String email;
    public LocalDate createdAt;
    public LocalDate updatedAt;
}
