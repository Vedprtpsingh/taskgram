package com.ved.taskgram.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ved.taskgram.dto.UserDto;
import com.ved.taskgram.entity.Role;
import com.ved.taskgram.entity.User;
import com.ved.taskgram.repository.RoleRepository;
import com.ved.taskgram.repository.UserRepository;
import com.ved.taskgram.service.UserService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserDto userDto){
        User user =new User();
        user.setName(userDto.getFirstName()+" "+userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getEmail());
        //encripted password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role=roleRepository.findByName("ROLE_USER");
        if(role==null){
            role=checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    private Role checkRoleExist() {
        Role role=new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users=userRepository.findAll();
        return users.stream().map((user)->mapToUserDto(user)).collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto=new UserDto();
        String[] str=user.getName().split(" ");
        userDto.setId(user.getId());
        userDto.setFirstName(user.getName().split(" ")[0]);
        userDto.setLastName(user.getName().split(" ")[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
