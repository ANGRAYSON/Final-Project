package com.hvacparts.parts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.hvacparts.parts.entity.PartsOut;
import com.hvacparts.parts.service.PartsOutService;

@RestController
public class DefaultPartsOutController implements PartOutOrderController{

  @Autowired
  private PartsOutService partsOutService;
  
  public PartsOut createPartOutOrder(PartsOut partsOut) {
    return partsOutService.createPartOutOrder(partsOut);
  }

}
