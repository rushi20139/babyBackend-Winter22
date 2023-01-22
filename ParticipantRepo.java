package com.babylogging.babybackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.babylogging.babybackend.entities.Participant;

public interface ParticipantRepo extends JpaRepository<Participant, Integer>{

}
