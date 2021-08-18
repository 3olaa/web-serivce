package com.SW.businessLogic;

import com.SW.DA.UserDA;
import com.SW.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServices implements IUserServices {
    @Autowired
    private UserDA userDA;

    @Transactional
    @Override
    public void createUser (User user) {
        userDA.addUser ( user );
    }

    @Transactional
    @Override
    public Object getCurrentUsers (String name) {
        return userDA.retrieveRegisteredUsers (name);
    }

    @Transactional
    @Override
    public User getUser(String email){
        return userDA.retrieveUserByEmail (email);
    }

}
