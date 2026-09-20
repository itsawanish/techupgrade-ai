package com.techupgrade.ai.user.service;

import com.techupgrade.ai.user.exception.EmailAlreadyExistException;
import com.techupgrade.ai.user.dto.UserCreateRequest;
import com.techupgrade.ai.user.dto.UserResponse;
import com.techupgrade.ai.user.entity.User;
import com.techupgrade.ai.user.entity.UserStatus;
import com.techupgrade.ai.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Transactional
    public UserResponse createUser(UserCreateRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistException("Email already Registered");
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setStatus(UserStatus.ACTIVE);

        User savedUser=userRepository.save(user);

        return mapToResponse(savedUser);
    }
    private UserResponse mapToResponse(User user){
        UserResponse response= new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setStatus(user.getStatus());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

}
