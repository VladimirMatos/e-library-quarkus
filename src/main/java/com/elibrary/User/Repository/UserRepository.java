package com.elibrary.User.Repository;
import com.elibrary.User.Model.UserModel;
import com.elibrary.utils.GenericRepository;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class UserRepository implements GenericRepository<UserModel> {


    public UserModel findByEmail(String email){
        return find("email", email).firstResult();
    }

}
