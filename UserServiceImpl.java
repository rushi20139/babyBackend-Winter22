package com.babylogging.babybackend.services.impl; 
import com.babylogging.babybackend.exceptions.*;

import java.util.List;
import java.util.Optional; 
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.babylogging.babybackend.entities.User;
import com.babylogging.babybackend.payloads.UserDto; 
import com.babylogging.babybackend.repositories.UserRepo; 
import com.babylogging.babybackend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

 @Autowired
 private UserRepo userRepo;
 @Autowired
 private ModelMapper modelMapper;
 @Override
 public UserDto createUser(UserDto userDto) {

    User user=this.dtoToUser(userDto);
    User savedUser=this.userRepo.save(user);
    return this.userToDto(savedUser);
  }

@Override
public UserDto authenticateUser(String email, String password) throws Exception {
    UserDto userDto = null;
    System.out.println(userRepo.findByEmail(email));
    Optional<User> optionalUser = Optional.of(userRepo.findByEmail(email)); 
    User user = optionalUser
            .orElseThrow(()-> new Exception("Invalid Username"));
    if(!password.equals(user.getPassword()))
       throw new Exception("Invalid Password");

    userDto= new UserDto();
    userDto.setEmail(user.getEmail());
    userDto.setName(user.getName()); 
    userDto.setMobileNumber(user.getMobileNumber());
    userDto.setPassword(user.getPassword());
    return userDto;

  }

@Override
public UserDto update(UserDto userDto, Integer userId) {
     User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId)); 
     user.setName(userDto.getName()); 
     user.setEmail(userDto.getEmail());
     user.setPassword(userDto.getPassword()); 
     user.setMobileNumber(userDto.getMobileNumber());

    User updatedUser=this.userRepo.save(user); 
    UserDto userDto1=this.userToDto(updatedUser);

    return userDto1;

 }

@Override
public UserDto getUserById(Integer userId) {

    User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId)); 
    return this.userToDto(user);
  }

@Override
public UserDto getUserByEmail(String email) {
    
     User user=this.userRepo.findByEmail(email):
     if(user!=null) { 
        UserDto userDto= new UserDto();
        userDto.setEmail(user.getEmail()); 
        userDto.setName(user.getName());
        userDto.setMobileNumber(user.getMobileNumber()); 
        userDto.setPassword(user.getPassword());
        return userDto;
      }
      else {
        return null;
      }
   }

@Override
public List<UserDto> getAllUsers() {

    List<User> users = this.userRepo.findAll();
    List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList()); 
    return userDtos;
  }

@Override
public void deleteUser(Integer userId) {
    User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId));
    this.userRepo.delete(user);
  }

 public User dtoToUser(UserDto userDto) { 
      User user=this.modelMapper.map(userDto, User.class); 
      //user.setld(userDto.getId());
      //user.setMobileNumber(userDto.getMobileNumber()); 
      //user.setPassword(userDto.getPassword());
      //user.setName(userDto.getName()); 
      //user.setEmail(userDto.getEmail());
      return user;
  } 

public UserDto userToDto(User user) {
      UserDto userDto=this.modelMapper.map(user, UserDto.class);
    // userDto.setId(user.getId()); 
    // userDto.setEmail(user.getEmail()); 
    //userDto.setMobileNumber(user.getMobileNumber());
    // userDto.setName(user.getName()); 
    //userDto.setPassword(user.getPassword());
      return userDto;
   }
 }
