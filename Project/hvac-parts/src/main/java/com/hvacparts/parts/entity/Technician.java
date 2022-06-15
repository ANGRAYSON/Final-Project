package com.hvacparts.parts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Technician {
  
  private Integer employee_num;
  private String first_name;
  private String last_name;
  private ActiveStatus status;
  
  
  public boolean isValid(Technician tech) {
    if(employee_num == null || employee_num == 0) {
      return false;
    }
    
    if(first_name == null || first_name.isEmpty()) {
      return false;
    }
    
    if(last_name == null || last_name.isEmpty()) {
      return false;
    }
    
    return true;
  }

}
