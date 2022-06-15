package com.hvacparts.parts.dao;

import java.util.List;
import java.util.Optional;
import com.hvacparts.parts.entity.Technician;

public interface TechnicianDao {

  List<Technician> showAllTechs();

  Optional<Technician> showSpecificTech(Integer employee_num);

  Optional<Technician> save(Technician tech);

}
