package com.pranshu.linkedInProject.usersService.service;

import com.pranshu.linkedInProject.usersService.dto.LoginRequestDto;
import com.pranshu.linkedInProject.usersService.dto.SignUpRequestDto;
import com.pranshu.linkedInProject.usersService.dto.UserDto;
import com.pranshu.linkedInProject.usersService.entity.User;
import com.pranshu.linkedInProject.usersService.exception.BadRequestException;
import com.pranshu.linkedInProject.usersService.repository.UserRepository;
import com.pranshu.linkedInProject.usersService.utils.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        log.info("SignUp user with email : {}", signUpRequestDto.getEmail());
        boolean emailAlreadyExists = userRepository.existsByEmail(signUpRequestDto.getEmail());
        if(emailAlreadyExists){
            throw new BadRequestException("Email already exists");
        }
        User user = modelMapper.map(signUpRequestDto,  User.class);
        user.setPassword(BCrypt.hash(user.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {
        log.info("Login user with email : {}", loginRequestDto.getEmail());
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()
                -> new BadRequestException("Incorrect email or password"));

        boolean isPasswordMatch = BCrypt.match(loginRequestDto.getPassword(), user.getPassword());

        if(!isPasswordMatch) {
            throw new BadRequestException("Incorrect email or password");
        }
        return jwtService.generateAccessToken(user);
    }
}
