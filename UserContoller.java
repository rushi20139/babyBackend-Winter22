package com.babylogging.babybackend.controllers;

import java.util.List; 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping:

import org.springframework.web.bind.annotation.RestController;

import com.babylogging.babybackend.payloads.ApiResponse; 
import com.babylogging.babybackend.payloads.UserCredDto; 
import com.babylogging.babybackend.payloads.UserDto; 
import com.babylogging.babybackend.services.UserService; 
import jakarta.validation.Valid;

@RestController

@CrossOrigin

@RequestMapping("/api/users") 

public class UserController {

  @Autowired
  private UserService userService;

  //post method to validate User credentials

  @PostMapping("/login")
  public ResponseEntity<UserDto>authenticateUser(@Valid @RequestBody UserCredDto userCredDto)
  throws Exception {
 
  System.out.println(userCredDto);

  System.out.println("CUSTOMER TRYING TO LOGIN, VALIDATING CREDENTIALS. CUSTOMER EMAIL ID: " + userCredDto.getEmail()); 
  UserDto userd=userService.getUserByEmail(userCredDto.getEmail());

  if(userd!=null) {

  UserDto userDtoFromDb = userService.authenticateUser(userCredDto.getEmail(), userCredDto.getPassword());

  System.out.println("CUSTOMER LOGIN SUCCESS, CUSTOMER EMAIL: " + userDtoFromDb.getEmail());
  return new ResponseEntity<>(userDtoFromDb, HttpStatus.OK);
 }

else {

  System.out.println("USER NOT FOUND WITH ENTERED EMAIL ID, LOGIN FAILED!");
  return new ResponseEntity<>(userd, HttpStatus.BAD_REQUEST);
  }
}

  //post-method to create user

  @PostMapping("/")

  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

  String tempEmail=userDto.getEmail():

  System.out.println(tempEmail);

  UserDto userd=this.userService.getUserByEmail(tempEmail);

  if(userd!=null) {

       System.out.println("User Already Exists");

       System.out.println("User Already Exists");
       return new ResponseEntity<>(userd, HttpStatus.BAD_REQUEST);
       }

  else {

       UserDto createUserDto=this.userService.createUser(userDto); 
       return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
       }
}

//put-method to update user

 @PutMapping("/{userId}") 
 public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){ 
 UserDto updatedUser=this.userService.update(userDto, uid); 
 return ResponseEntity.ok(updatedUser);
 }

//delete-method to delete user

 @DeleteMapping("/{userId)")

 public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){

 this.userService.deleteUser(uid);

 return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK):

}

// get-method to get All user

 @GetMapping("/")

 public ResponseEntity<List<UserDto>> getAllUsers(){ 
    return ResponseEntity.ok(this.userService.getAllUsers());

  }
// get-method to get Single user
  @GetMapping("/{userId}") 
  public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userld){
 return ResponseEntity.ok(this.userService.getUserById(userld));
  }
}
