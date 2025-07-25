package com.ved.taskgram.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ved.taskgram.entity.User;
import com.ved.taskgram.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(usernameOrEmail);
        if(user!=null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getRoles().stream()
            .map((role)->new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toSet()));
        }else{
            throw new UsernameNotFoundException("Invalid email or password!");
        }
    }
}