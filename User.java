package com.babylogging.babybackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//If code doesn't work, remove Lombok

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 @Entity
 @Table(name="users")
 @NoArgsConstructor
 @Getter 
 @Setter

 public class User {

 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private int id;

 @Column(name="user_name", nullable=false)
 private String name; 
 private String email;
 private String password;
 private String mobileNo;

 public String getName() {
 return name;
 }

 public void setName(String name) {
 this.name=name;
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
 this.email=email;
 }

 public String getMobileNumber() {
 return mobileNo;
 }

 public void setMobileNumber(String mobileNo) { 
 this.mobileNo = mobileNo;
 }

 public Integer getld() {
 return id;
 } 

 public void setId(int id){
  this.id=id;
 }

}
