package com.code.export.service;

import com.code.export.dto.UserRequestDto;
import com.code.export.model.User;

import java.util.List;

public interface IUserServices {
    public List<User> findAllUsers();

    public User saveUser(UserRequestDto userRequestDto);
}
