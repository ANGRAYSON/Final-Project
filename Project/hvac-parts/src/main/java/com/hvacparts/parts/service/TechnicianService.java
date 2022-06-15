package com.hvacparts.parts.service;

import java.util.List;
import com.hvacparts.parts.entity.ActiveStatus;
import com.hvacparts.parts.entity.Technician;

public interface TechnicianService {

  List<Technician> showAllTechs();

  Technician showSpecificTech(Integer employee_num);

  Technician addNewTechnician(Technician tech);

  Technician updateTech(Integer employee_num, String first_name, String last_name, ActiveStatus status);

  
  
  

}
