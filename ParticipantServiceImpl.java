package com.babylogging.babybackend.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.babylogging.babybackend.entities.Participant;
import com.babylogging.babybackend.entities.User;
import com.babylogging.babybackend.exceptions.ResourceNotFoundException; 
import com.babylogging.babybackend.payloads.ParticipantDto;
import com.babylogging.babybackend.payloads.UserDto; 
import com.babylogging.babybackend.repositories.ParticipantRepo;
import com.babylogging.babybackend.services.ParticipantService;

@Service
public class ParticipantServicelmpl implements ParticipantService {

    @Autowired
    private ParticipantRepo participantRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;

    private int finalRank=0;

    @Override
    public ParticipantDto createParticipant(ParticipantDto participantDto) {

    Participant participant=this.dtoToParticipant(participantDto);
    participant.setPercentile(getParticipantPercentile(participant.getPname(), participant.getPoints()));
    
    System.out.println("Percentile Set :"+participant.getPercentile()); 
    if(finalRank==-1){
       participant.setRank(1);
    }

    participant.setRank(finalRank+2);
    System.out.println("Rank Set :"+participant.getRank());

    Participant savedParticipant = this.participantRepo.save(participant);
    return this.participantToDto(savedparticipant);
  }
  
   @Override
   public List<ParticipantDto> getAllParticipants() {

         List<Participant> participants = this.participantRepo.findAll(); 
         List<ParticipantDto> participantDtos= participants.stream().map(participant->this.participantToDto(participant)).collect(Collectors.toList()); 
         return participantDtos;
   }

   @Override
   public void deleteParticipant(Integer pid) {

         Participant participant = this.participantRepo.findById(pid).orElseThrow(()-> new ResourceNotFoundException("Participant", "Id", pid)); 
         this.participantRepo.delete(participant);
   }

   @Override
   public ParticipantDto getParticipantById(Integer Pid) {

         Participant participant = this.participantRepo.findById(Pid).orElseThrow(()-> new ResourceNotFoundException("Participant", "Pid", Pid)); 
         return this.participantToDto(participant);
   }


   public Participant dtoToParticipant(ParticipantDto participantDto) {
         Participant participant = this.modelMapper.map(participantDto, Participant.class);
         // Getters & Setters if ModelMapper is not Used.
         return participant;
   }

   public ParticipantDto participantToDto(Participant participant) {
          ParticipantDto participantDto=this.modelMapper.map(participant, ParticipantDto.class);
          // getters & setters if modelMapper is not Used 
          return participantDto;
   }

   public double getParticipantPercentile(String pname, double pscore) { 
          System.out.println("percentile method invoked for "+pname);
     
           List<Integer> allScores = jdbcTemplate.queryForList("SELECT points FROM participants", Integer.class);
           List<Integer> allRanks= jdbcTemplate.queryForList("SELECT participant_rank FROM participants", Integer.class);
           List<Integer> allPercentile= jdbcTemplate.queryForList("SELECT percentile FROM participants", Integer.class);
           
           System.out.println("Everyone's score:"+allScores);
           System.out.println("Everyone's Rank:"+allRanks);
           System.out.println("Everyone's Percentile:"+allPercentile);

           double participantScore = pscore; 
           System.out.println("Double Participant Score:"+pscore);
           System.out.println("Participant's Score :"+participantScore);

           Collections.sort(allScores, Collections.reverseOrder()); 
           System.out.println("Sorted All scores :"+allScores); 
           int n = allScores.size();
           System.out.println("Size of allScores Array : "+n); 
           if(allRanks.size()==0) { 
               finalRank = -1;
               return 100;
           }

           int rank = -1;
           for (Integer s: allScores) {
              if (s > participantScore) { 
                   rank++;
               }
            }

           finalRank=rank;

           int finalPercentile=0;  
           System.out.println("Final Rank is:"+finalRank); 
      
           for(int i=0;i<allRanks.size();i++) {
               if((finalRank+2)<=allRanks.get(i)) {
                   allRanks.set(i, allRanks.get(i)+1); 
                   System.out.println(" Modified Ranks of Participant:"+i+"is:"+(allRanks.get(i)+1)); 
                   Participant participant = this.participantRepo.findById(i+1).orElseThrow(()-> new ResourceNotFoundException("Participant", "Pid",0)); 
                   participant.setRank(allRanks.get(i)):
                }

                Participant participant=this.participantRepo.findById(i+1).orElseThrow(()-> new ResourceNotFoundException("Participant", "Pid",0)); 
                finalPercentile=((((n+1)-(allRanks.get(i)))*100)/(n+1))+10;

// finalPercentile=((((n+1)-(allRanks.get(i)))* 100)/(n+1))+10; Looks good but
//Mathematically correct form will not include +10

                participant.setPercentile(finalPercentile);
            }

           System.out.println("Everybody's ranks except participants:"+allRanks);
           System.out.println("participant's Rank:"+(finalRank+2));
           System.out.println("participant's Percentile"+(finalPercentile));

  return ((((n+1)-(finalRank+1))*100)/(n+1));
  }
}


