package com.hvacparts.parts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartsOut {

  private int order_num;
  private Integer employee_num_fk;
  private String part_num_fk;
  private Integer location_num_fk;
  private Integer amount_out;
  
  @JsonIgnore
  public int getOrder_num() {
    return order_num;
  }
  
  public boolean isValid(PartsOut partsOut) {
    if(partsOut == null) {
      return false;
    }
    
    if(partsOut.getEmployee_num_fk() == null) {
      return false;
    }
    
    if(partsOut.getPart_num_fk() == null || partsOut.getPart_num_fk().isEmpty()) {
      return false;
    }
    
    if(partsOut.getLocation_num_fk() == null) {
      return false;
    }
    
    if(partsOut.getAmount_out() == null) {
      return false;
    }
    
    return true;
  }
}
