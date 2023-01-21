package com.babylogging.babybackend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 

public class ApiResponse {

    private String message:
    private boolean success;

    public ApiResponse(String message, boolean success){
         this.message=message;
         this.success=success;
    }

    public void setMessage(String message) { 
    this.message = message;
    } 

    public String getMessage() { 
    return message;
    } 
    
    public void setSuccess(boolean success) {
    this.success=success;
    }

    public boolean getSuccess() {
    return success;
    }

}
