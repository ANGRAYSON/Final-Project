package com.hvacparts.parts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

  private Integer location_num;
  private String name;
  public boolean isValid(Location location) {
    if(location == null) {
      return false;
    }
    if(location.getLocation_num() == null) {
      return false;
    }
    if(location.getName() == null) {
      return false;
    }
    return true;
  }
}
