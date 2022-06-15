package com.hvacparts.parts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Part {

  private String part_num;
  private String name;
  private Type type;
  
  
  public boolean isValid(Part part) {
    if(part == null) {
      return false;
    }
    if(part.getPart_num() == null) {
      return false;
    }
    
    if(part.getName() == null || part.getName().isEmpty()) {
      return false;
    }
    return true;
  }

}
