package com.hvacparts.parts.dao;

import java.util.Optional;
import com.hvacparts.parts.entity.PartsOut;

public interface PartsOutDao {

  Optional<PartsOut> createPartOutOrder(PartsOut partsOut);

  boolean checkForEmployee(Integer employee_num_fk);

}
