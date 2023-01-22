package com.babylogging.babybackend.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

 private int id;
 private String name;
 @Email(message="Email Address not valid!!")
 private String email;
 @NotEmpty
 @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{3,}$",
 message="Invalid Password: Password must be "
 + "Minimum 3 and maximum 10 characters, at least one uppercase letter,"
 + "one lowercase letter, one number and one special character:")
 
 private String password; 
 @NotNull @Size(min=10,max=10,message="Mobile number must be 10 digits") 
 private String mobileNo;

 public String getName() { 
 return name;
 } 

 public void setName(String name) { 
 this.name = name;
 } 

 public String getEmail() { 
 return email;
 }

 public void setPassword(String password) { 
  this.password = password;
 } 

 public String getPassword() { 
  return password;
 }

 public void setEmail(String email) { 
 this.email = email;
 } 

 public String getMobileNumber() { 
 return mobileNo;//look here 
 }

 public void setMobileNumber(String mobileNo) { 
  this.mobileNo = mobileNo;
 }

 public Integer getld() { 
  return id;
 } 

 public void setId(int id) { 
  this.id=id;
 }

}
