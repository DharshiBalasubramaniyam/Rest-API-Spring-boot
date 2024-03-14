package com.example.demo.services;

import com.example.demo.dtos.ApiResponseDto;
import com.example.demo.dtos.ApiResponseStatus;
import com.example.demo.dtos.UserDetailsRequestDto;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.exceptions.UserServiceLogicException;
import com.example.demo.modals.User;
import com.example.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> registerUser(UserDetailsRequestDto newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException {

        try {
            if (userRepository.findByEmail(newUserDetails.getEmail()) != null){
                throw new UserAlreadyExistsException("Registration failed: User already exists with email " + newUserDetails.getEmail());
            }
            if (userRepository.findByUsername(newUserDetails.getUserName()) != null){
                throw new UserAlreadyExistsException("Registration failed: User already exists with username " + newUserDetails.getUserName());
            }

            User newUser = new User(
                    newUserDetails.getUserName(), newUserDetails.getEmail(), newUserDetails.getPhone(), LocalDateTime.now()
            );

            // save() is an in built method given by JpaRepository
            userRepository.save(newUser);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "New user account has been successfully created!"));

        }catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        }catch (Exception e) {
            log.error("Failed to create new user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceLogicException {
        try {
            List<User> users = userRepository.findAllByOrderByRegDateTimeDesc();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), users)
                    );

        }catch (Exception e) {
            log.error("Failed to fetch all users: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateUser(UserDetailsRequestDto newUserDetails, int id)
            throws UserNotFoundException, UserServiceLogicException {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

            user.setEmail(newUserDetails.getEmail());
            user.setUsername(newUserDetails.getUserName());
            user.setPhone(newUserDetails.getPhone());

            userRepository.save(user);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User account updated successfully!")
                    );

        }catch(UserNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        }catch(Exception e) {
            log.error("Failed to update user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(int id) throws UserServiceLogicException, UserNotFoundException {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

            userRepository.delete(user);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User account deleted successfully!")
                    );
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to delete user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }
}
