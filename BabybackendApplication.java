package com.babylogging.babybackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BabybackendApplication {

   public static void main(String[] args) {
       SpringApplication.run(BabybackendApplication.class, args);

   }
   @Bean
   public ModelMapper modelMapper() {

      return new ModelMapper();
 
  }
}
