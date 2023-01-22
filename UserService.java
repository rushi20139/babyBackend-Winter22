package com.babylogging.babybackend.services;

import java.util.List;

import com.babylogging.babybackend.payloads.UserDto;


public interface UserService {

  UserDto createUser(UserDto user);
  UserDto update(UserDto user, Integer userId); 
  UserDto getUserById(Integer userId);
  List<UserDto> getAllUsers(); 
  void deleteUser(Integer userId);
  UserDto getUserByEmail(String email);
  UserDto authenticateUser(String email, String password) throws Exception;

}
