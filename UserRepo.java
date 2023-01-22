package com.babylogging.babybackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babylogging.babybackend.entities.User;
import com.babylogging.babybackend.payloads.UserDto;

public interface UserRepo extends JpaRepository<User, Integer>{

User findByEmail(String email);

}
