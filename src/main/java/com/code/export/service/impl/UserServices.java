package com.code.export.service.impl;

import com.code.export.model.User;
import com.code.export.repository.UserRepository;
import com.code.export.service.IUserServices;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices implements IUserServices {
    private final UserRepository userRepository;

    private final String SORT_FIELD = "email";

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll(Sort.by(SORT_FIELD).ascending());
    }


}
