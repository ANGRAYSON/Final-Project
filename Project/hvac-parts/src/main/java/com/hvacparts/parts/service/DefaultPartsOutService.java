package com.hvacparts.parts.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.hvacparts.parts.dao.DefaultLocationsDao;
import com.hvacparts.parts.dao.DefaultPartsDao;
import com.hvacparts.parts.dao.PartsOutDao;
import com.hvacparts.parts.entity.Location;
import com.hvacparts.parts.entity.Part;
import com.hvacparts.parts.entity.PartsOut;

@Service
public class DefaultPartsOutService implements PartsOutService{

  @Autowired
  private PartsOutDao partsOutDao;
  
  @Autowired
  private DefaultLocationsDao locationsDao;
  
  @Autowired
  private DefaultPartsDao partsDao;
  
  
  public PartsOut createPartOutOrder(PartsOut partsOut) {
    if(!partsOut.isValid(partsOut)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something is wrong with the information. Please check again.");
    }
    Optional<Location> location = locationsDao.getSpecificLocation(partsOut.getLocation_num_fk());
    Optional<Part> part = partsDao.getSpecificPart(partsOut.getPart_num_fk());
    boolean employee = partsOutDao.checkForEmployee(partsOut.getEmployee_num_fk());
    
    if(location.isEmpty() || part.isEmpty() || employee == false) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Looks like one of those items doesn't exist. Please check again.");
    }
    
    
    Optional<PartsOut> order = partsOutDao.createPartOutOrder(partsOut);
    if(order.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Looks like that part doesn't have any stock in that location. Please check again.");
    }
    return order.get();
  }

}
