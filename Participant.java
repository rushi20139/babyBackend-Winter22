package com.babylogging.babybackend.entities;

import jakarta.persistence.Column;

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id; 
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;

 @Entity

 @Table(name="participants")
 public class Participant {

 @ld
 @GeneratedValue(strategy=GenerationType.IDENTITY) 
 private int pid;

 @NotNull
 private String pname;
 private float points;
 private String timeTaken;
 private double percentile;

 @Column(name="participant_rank")
 private int rank;

 public Integer getPid() {
 return pid;
 }

 public void setPid(int pid) { 
  this.pid = pid;
 } 

 public String getPname() { 
  return pname;
 } 

 public void setPname(String pname) {
  this.pname = pname;
 } 

 public float getPoints() {
  return points;
 } 

 public void setPoints(float points) { 
 this.points = points;
 } 
 
 public String getTimeTaken() { 
 return timeTaken;
 }

 public void setTimeTaken(String timeTaken) { 
  this.timeTaken = timeTaken;
 } 

 public double getPercentile() {
  return percentile;
 } 

 public void setPercentile(double d) { 
 this.percentile = d;
 } 

 public Integer getRank() {
  return rank;
 }

 public void setRank(int rank){
 this.rank=rank;
 }

}
