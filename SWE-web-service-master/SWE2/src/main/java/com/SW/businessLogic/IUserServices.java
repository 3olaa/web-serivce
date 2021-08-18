package com.SW.businessLogic;

import com.SW.model.User;

public interface IUserServices {
    Object getCurrentUsers(String name);
    void  createUser(User user);
    public User getUser(String email);
}
