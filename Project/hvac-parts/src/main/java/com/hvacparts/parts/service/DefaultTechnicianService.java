package com.hvacparts.parts.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.hvacparts.parts.dao.DefaultTechnicianDao;
import com.hvacparts.parts.entity.ActiveStatus;
import com.hvacparts.parts.entity.Technician;

@Service
public class DefaultTechnicianService implements TechnicianService{
  
  @Autowired
  private DefaultTechnicianDao techDao;

  public List<Technician> showAllTechs() {
    List<Technician> techs = techDao.showAllTechs();
    if (techs.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no technicians were found.");
    }
    return techs;
  }

  public Technician showSpecificTech(Integer employee_num) {
    if (employee_num == null || employee_num == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hmm something is wrong with the employee number");
    }
    Optional<Technician> tech = techDao.showSpecificTech(employee_num);
    if (tech.isPresent()) {
      return tech.get();
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no technicians with that ID were found.");
  }

  public Technician addNewTechnician(Technician tech) {
    if (!tech.isValid(tech)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something is wrong with the information you entered. Please check again");
    }
    Integer employee_num = tech.getEmployee_num();
    Optional<Technician> exists = techDao.showSpecificTech(employee_num);
    if(exists.isPresent()) {
      throw new RuntimeException("Looks like that technician arleady exists! Try a different employee number");
    }
    Optional<Technician> result = techDao.save(tech);
    return result.get();
  }

  public Technician updateTech(Integer employee_num, String first_name, String last_name, ActiveStatus status) {
    if (employee_num != null) {
      Technician tech = Technician.builder().employee_num(employee_num).first_name(first_name).last_name(last_name).status(status).build();
      if (tech.isValid(tech)) {
        techDao.save(tech);
        return tech;
      }
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no technicians with that ID were found.");
  }


}
