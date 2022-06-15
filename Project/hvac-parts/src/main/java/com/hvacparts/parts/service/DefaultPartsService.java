package com.hvacparts.parts.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.hvacparts.parts.dao.DefaultInventoryDao;
import com.hvacparts.parts.dao.DefaultPartsDao;
import com.hvacparts.parts.entity.Inventory;
import com.hvacparts.parts.entity.Part;
import com.hvacparts.parts.entity.Type;

@Service
public class DefaultPartsService implements PartsService{

  @Autowired
  private DefaultPartsDao partsDao;
  
  @Autowired
  private DefaultInventoryDao inventoryDao;
  
  public List<Part> showAllParts() {
    
    List<Part> parts = partsDao.showAllParts();
    
    if(parts.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no parts were found.");
    }
    return parts;
    
  }

  
  public Part getSpecificPart(String part_num) {
    if(part_num == null || part_num.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Looks like something is wrong with the part number.");
    }
    Optional<Part> part = partsDao.getSpecificPart(part_num);
    if(part.isPresent()) {
      return part.get();
    }
    
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no parts with that ID were found.");
    
  }

  public Part addNewPart(Part part) { 
    if(!part.isValid(part)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Looks like something is wrong with the information.");
    }
    String part_num = part.getPart_num();
    List<Inventory> existing = inventoryDao.getSpecificPartInventory(part_num);
    if(existing.isEmpty()) {
      Optional<Part> result = partsDao.save(part);
      inventoryDao.createInventory(part);
      return result.get();
    }
    throw new RuntimeException("Hmmm, looks like you already have that part");
  }

  public Part updatePart(String part_num, String part_name, Type type){
    if(part_num != null) {
      Part part = Part.builder()
          .part_num(part_num)
          .name(part_name)
          .type(type)
          .build();
      if(part.isValid(part)){
        partsDao.save(part);
        return part;
      }
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no parts were found to update.");
    
  }

  public Part deletePart(String part_num){
    if(part_num != null) {
      Part part = getSpecificPart(part_num);
      partsDao.delete(part);
      return part;
    }else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no parts were found to delete.");
    }
  }


}
