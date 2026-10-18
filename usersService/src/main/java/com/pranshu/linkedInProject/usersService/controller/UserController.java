package com.pranshu.linkedInProject.usersService.controller;

import com.pranshu.linkedInProject.usersService.dto.LoginRequestDto;
import com.pranshu.linkedInProject.usersService.dto.SignUpRequestDto;
import com.pranshu.linkedInProject.usersService.dto.UserDto;
import com.pranshu.linkedInProject.usersService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto user) {
        UserDto userDto = authService.signUp(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto user) {
        String token = authService.login(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
