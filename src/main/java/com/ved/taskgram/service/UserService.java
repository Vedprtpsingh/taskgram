package com.ved.taskgram.service;

import java.util.List;

import com.ved.taskgram.dto.UserDto;
import com.ved.taskgram.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
