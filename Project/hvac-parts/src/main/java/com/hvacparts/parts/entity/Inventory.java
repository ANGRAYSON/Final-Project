package com.hvacparts.parts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
  
  private String part_num_fk;
  private String part_name;
  private int location_num_fk;
  private String location_name;
  private int stock;
 

}
