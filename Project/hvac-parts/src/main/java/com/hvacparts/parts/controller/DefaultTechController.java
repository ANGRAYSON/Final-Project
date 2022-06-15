package com.hvacparts.parts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.hvacparts.parts.entity.ActiveStatus;
import com.hvacparts.parts.entity.Technician;
import com.hvacparts.parts.service.DefaultTechnicianService;

@RestController
public class DefaultTechController implements TechnicianController{
  
  @Autowired
  private DefaultTechnicianService techService;

  public List<Technician> showAllTechnicians() {
    return techService.showAllTechs();
  }

  public Technician showSpecificTech(Integer employee_num) {
    return techService.showSpecificTech(employee_num);
  }

  public Technician addNewTechnician(Technician tech) {
    return techService.addNewTechnician(tech);
  }

  public Technician updateTechnician(Integer employee_num, String first_name, String last_name, ActiveStatus status) {
    return techService.updateTech(employee_num, first_name, last_name, status);
  }


}
