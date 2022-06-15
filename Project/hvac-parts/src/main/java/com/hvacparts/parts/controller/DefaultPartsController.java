package com.hvacparts.parts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.hvacparts.parts.entity.Part;
import com.hvacparts.parts.entity.Type;
import com.hvacparts.parts.service.DefaultPartsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultPartsController implements PartsController{

  @Autowired
  private DefaultPartsService partsService;
  
  public List<Part> showAllParts() {
    return partsService.showAllParts();
  }

  public Part showSpecificPart(String part_num) {
    log.debug(part_num);
    return partsService.getSpecificPart(part_num);
  }

  public Part addNewPart(Part part) {
    return partsService.addNewPart(part);
  }

  public Part updatePart(String part_num,String part_name, Type type){
    return partsService.updatePart(part_num, part_name, type);
  }

  public Part deletePart(String part_num) {
    return partsService.deletePart(part_num);
  }

}
