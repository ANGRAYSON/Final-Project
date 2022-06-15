package com.hvacparts.parts.service;

import java.util.List;
import com.hvacparts.parts.entity.Inventory;

public interface InventoryService {

  List<Inventory> showAllInventory();

  List<Inventory> getSpecificPartInventory(String part_num);

  Inventory updatePartInventory(String part_num, Integer location_num, Integer stock);

  List<Inventory> getInventoryByLocation(Integer location_num);
  
  Inventory createNewInventoryPartsAtLocation(Integer location_num, Integer stock, String part_num) throws Exception;

}
