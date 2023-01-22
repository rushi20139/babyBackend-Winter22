package com.babylogging.babybackend.payloads;

 public class UserCredDto {

 private String emailId;
 private String password;

 public String getEmail() {
 return emailId;
 }

 public void setEmail(String emailId) {
 this.emailId=emailId;
 }

 public String getPassword() {
 return password;
 }

 public void setPassword(String password) { 
 this.password = password;
 }

}
