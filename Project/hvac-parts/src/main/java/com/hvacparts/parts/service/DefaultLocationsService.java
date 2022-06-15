package com.hvacparts.parts.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.hvacparts.parts.dao.DefaultLocationsDao;
import com.hvacparts.parts.entity.Inventory;
import com.hvacparts.parts.entity.Location;

@Service
public class DefaultLocationsService implements LocationsService {

  @Autowired
  private DefaultLocationsDao locationsDao;

  @Autowired
  private DefaultInventoryService inventoryService;

  public List<Location> showAllLocations() {
    List<Location> locations = locationsDao.showAllLocations();
    if (locations.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no locations were found.");
    }
    return locations;
  }

  public Location getSpecificLocation(Integer location_num) {
    if (location_num == null || location_num == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Looks like something is wrong with that location number.");
    }
    Optional<Location> location = locationsDao.getSpecificLocation(location_num);
    if (location.isPresent()) {
      return location.get();
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no locations were found with that ID.");
  }

  public Location addNewLocation(Location location) throws Exception {
    if (!location.isValid(location)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something is wrong with the location information.");
    }
    Integer loctaion_num = location.getLocation_num();
    Optional<Location> exists = locationsDao.getSpecificLocation(loctaion_num);
    if(exists.isPresent()) {
      throw new RuntimeException("Looks like that location arleady exists! Try a different location number");
    }
    Optional<Location> result = locationsDao.save(location);
    return result.get();
  }

  public Location updateLocation(Integer location_num, String name) {
    if (location_num != null) {
      Location location = Location.builder().location_num(location_num).name(name).build();
      if (location.isValid(location)) {
        locationsDao.save(location);
        return location;
      }
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no locations were found to update.");
  }

  public Location deleteLocation(Integer location_num) {
    if (location_num != null) {
      Location location = getSpecificLocation(location_num);
      List<Inventory> inventory = inventoryService.getInventoryByLocation(location_num);
      if (!inventory.isEmpty()) {
        for (Inventory item : inventory) {
          int stockCurrentLocation = item.getStock();
          String part = item.getPart_num_fk();
          Optional<Inventory> shopStockInventory = inventoryService.getSpecificPartInventoryByLocation(part, 1);
          Inventory shopStockInventoryIndividual = shopStockInventory.get();
          int shopStock = shopStockInventoryIndividual.getStock();
          inventoryService.updatePartInventory(part, 1, (shopStock + stockCurrentLocation));
          inventoryService.updatePartInventory(part, location_num, 0);
        }
      }
      locationsDao.delete(location);
      return location;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hmm no locations were found to delete.");
    }
  }

}
