package com.hvacparts.parts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.hvacparts.parts.entity.Inventory;
import com.hvacparts.parts.service.DefaultInventoryService;

@RestController
public class DefaultInventoryController implements InventoryController{

  @Autowired 
  private DefaultInventoryService inventoryService;
  
  public List<Inventory> getAllInventory() {
    return inventoryService.showAllInventory();
  }

  public List<Inventory> getSpecificPartInventory(String part_num) {
    return inventoryService.getSpecificPartInventory(part_num);
  }

  public Inventory updatePartInventory(String part_num, Integer location_num, Integer stock) {
    return inventoryService.updatePartInventory(part_num, location_num, stock);
  }

  public List<Inventory> getInventoryByLocation(Integer location_num) {
    return inventoryService.getInventoryByLocation(location_num);
  }

  public Inventory createNewInventoryPartsAtLocation(Integer location_num, Integer stock, String part_num) throws Exception {
    return inventoryService.createNewInventoryPartsAtLocation(location_num, stock, part_num);
  }


}
