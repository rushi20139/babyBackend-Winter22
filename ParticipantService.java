package com.babylogging.babybackend.services;

import java.util.List;
import com.babylogging.babybackend.payloads.ParticipantDto;

public interface ParticipantService {

   ParticipantDto createParticipant(ParticipantDto participant);
   List<ParticipantDto> getAllParticipants(); 
   ParticipantDto getParticipantById(Integer templd); 
   public double getParticipantPercentile(String pname, double pscore);
   void deleteParticipant(Integer pid);

}
