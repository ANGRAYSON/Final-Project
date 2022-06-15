package com.hvacparts.parts.dao;

import java.util.List;
import java.util.Optional;
import com.hvacparts.parts.entity.Inventory;
import com.hvacparts.parts.entity.Part;

public interface InventoryDao {
  void createInventory(Part part);

  List<Inventory> showAllInventory();

  List<Inventory> getSpecificPartInventory(String part_num);

  void updateStock(String part_num, Integer location_num, Integer stock);

  List<Inventory> showAllInventorySpecificLocation(Integer location_num);

  Optional<Inventory> createNewInventoryPartsAtLocation(String part_num, Integer location_num, Integer stock);
}
