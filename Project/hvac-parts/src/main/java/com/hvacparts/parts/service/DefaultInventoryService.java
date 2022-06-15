package com.hvacparts.parts.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.hvacparts.parts.dao.DefaultInventoryDao;
import com.hvacparts.parts.dao.DefaultLocationsDao;
import com.hvacparts.parts.dao.DefaultPartsDao;
import com.hvacparts.parts.entity.Inventory;
import com.hvacparts.parts.entity.Location;
import com.hvacparts.parts.entity.Part;

@Service
public class DefaultInventoryService implements InventoryService{
  
  @Autowired
  private DefaultInventoryDao inventoryDao;
  
  @Autowired
  private DefaultPartsDao partsDao;
  
  @Autowired
  private DefaultLocationsDao locationsDao;

  public List<Inventory> showAllInventory() {
    List<Inventory> inventory = inventoryDao.showAllInventory();
    if(inventory.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no inventory to show");
    }
    return inventory;
  }

  
  public List<Inventory> getSpecificPartInventory(String part_num) {
    if(part_num == null || part_num.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something is wrong with the part number");
    }
    List<Inventory> inventory = inventoryDao.getSpecificPartInventory(part_num);
    if(inventory.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no inventory to show with that ID");
    }
    return inventory;
  }

  
  public Inventory updatePartInventory(String part_num, Integer location_num, Integer stock){
    Optional<Inventory> checkInventory = inventoryDao.getSpecificPartInventoryByLocation(part_num, location_num);
    if(checkInventory.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That part does not exsist in that location yet. Please add it to location to upate");
    }
    if(part_num.isBlank() || part_num == null || location_num == null || stock == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something is wrong with the information for that inventory.");
    }
    
    System.out.println(checkInventory.get());
    inventoryDao.updateStock(part_num, location_num, stock);
    Inventory inventory = Inventory.builder()
        .part_num_fk(part_num)
        .part_name(checkInventory.get().getPart_name())
        .location_num_fk(location_num)
        .location_name(checkInventory.get().getLocation_name())
        .stock(stock)
        .build();
    if(inventory == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no inventory to update");
    }
    return inventory;
  }

  
  public List<Inventory> getInventoryByLocation(Integer location_num) {
    if(location_num != null) {
      List<Inventory> inventory = inventoryDao.showAllInventorySpecificLocation(location_num);
      if(inventory.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no inventory to show in that location");
      }
      return inventory;
    }
    
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something is wrong with the location number. Please check again");
  }


  @SuppressWarnings("null")
  public Optional<Inventory> getSpecificPartInventoryByLocation(String part_num, Integer location_num) {
    if(location_num != null || part_num != null || !part_num.isEmpty()) {
      return inventoryDao.getSpecificPartInventoryByLocation(part_num, location_num);
    }
    throw new NoSuchElementException("Hmm, no inventory with that ID to show at that location");
  }


  @SuppressWarnings("null")
  public Inventory createNewInventoryPartsAtLocation(Integer location_num, Integer stock, String part_num) throws Exception {
      if(location_num != null || location_num != 0 || part_num != null || !part_num.isEmpty()) {
        Optional<Inventory> existsHere = inventoryDao.getSpecificPartInventoryByLocation(part_num, location_num);
        Optional<Part> exists = partsDao.getSpecificPart(part_num);
        Optional<Location> locationExists = locationsDao.getSpecificLocation(location_num);
        if(existsHere.isPresent()) {
          throw new RuntimeException("Looks like that part is already here! You need to update instead.");
        }
        if(!exists.isPresent()) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Looks like that part doesn't exist yet! Please add it to 'parts' first.");
        }
        if(!locationExists.isPresent()) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Looks like that location doesn't exist yet! Please add it to 'locations' first.");
        }
        
        Optional<Inventory> inventory = inventoryDao.createNewInventoryPartsAtLocation(part_num, location_num, stock);
        return inventory.get();
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something is wrong with that information. Please check again.");
  }

}
