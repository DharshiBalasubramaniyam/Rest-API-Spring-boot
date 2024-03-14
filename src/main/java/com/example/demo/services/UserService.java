package com.example.demo.services;

import com.example.demo.dtos.ApiResponseDto;
import com.example.demo.dtos.UserDetailsRequestDto;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.exceptions.UserServiceLogicException;
import com.example.demo.modals.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ResponseEntity<ApiResponseDto<?>> registerUser(UserDetailsRequestDto newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> getAllUsers()
            throws UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> updateUser(UserDetailsRequestDto newUserDetails, int id)
            throws UserNotFoundException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> deleteUser(int id)
            throws UserServiceLogicException, UserNotFoundException;

}
