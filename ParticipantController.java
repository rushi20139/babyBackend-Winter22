package com.babylogging.babybackend.controllers;

import java.util.List;

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

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController; 
import com.babylogging.babybackend.payloads.ApiResponse;

import com.babylogging.babybackend.payloads.ParticipantDto;

import com.babylogging.babybackend.payloads.UserDto;

import com.babylogging.babybackend.services.Participant Service;

import com.babylogging.babybackend.services.UserService;

import jakarta.validation.Valid;


@RestController

@CrossOrigin

@RequestMapping("/api/participants") 

public class ParticipantController {

  @Autowired

  private ParticipantService participantService;

  @PostMapping("/")

  public ResponseEntity<ParticipantDto> createParticipant (@Valid @RequestBody ParticipantDto participanDto)
 { 
   System.out.println("PARTICIPANT SUBMITTED THE TEST SUCCESSFULLY!!!");

   System.out.println("create participant called");

   ParticipantDto createParticipantDto=this.participantService.createParticipant(participantDto); 
   System.out.println("///"+createParticipantDto.getPname()+"///"+createParticipantDto.getPoints()+"///"); 
   return new ResponseEntity<>(createParticipantDto, HttpStatus.CREATED);

 }

@GetMapping("/")

  public ResponseEntity<List<ParticipantDto>> getAllParticipants(){

  return ResponseEntity.ok(this.participantService.getAllParticipants());
 }

@GetMapping("/percentile/{pname}")

 public double getParticipantPercentile(@PathVariable String pname, @PathVariable double pscore) { 
  System.out.println("Participant Score: "+pscore); 
  return this.participantService.getParticipantPercentile(pname, pscore);

}

@DeleteMapping("/{pid}")

 public ResponseEntity<?> deleteParticipant(@PathVariable("pid") Integer pid) {

  this.participantService.deleteParticipant(pid);
  return new ResponseEntity<ApiResponse>(new ApiResponse("Participant deleted Successfully", true), HttpStatus.OK);
 }
}
