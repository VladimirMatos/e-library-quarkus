package com.elibrary.User.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;



@Data
@MongoEntity(collection = "Users")
public class UserModel {

    @JsonProperty("_id")
    public ObjectId id;
    public String name;
    public String lastname;
    public String email;
    public String password;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;


    // Custom method to set createdAt and updatedAt
    public void initDates() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Custom method to update updatedAt
    public void updateDate() {
        this.updatedAt = LocalDateTime.now();
    }
}
