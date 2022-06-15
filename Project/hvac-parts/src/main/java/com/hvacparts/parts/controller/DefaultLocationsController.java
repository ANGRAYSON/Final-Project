package com.hvacparts.parts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.hvacparts.parts.entity.Location;
import com.hvacparts.parts.service.DefaultLocationsService;

@RestController
public class DefaultLocationsController implements LocationsController{

  @Autowired
  private DefaultLocationsService locationsService;
  
  public List<Location> showAllLocations() {
    return locationsService.showAllLocations();
  }

  public Location showSpecificLocation(Integer location_num) {
    return locationsService.getSpecificLocation(location_num);
  }

  public Location addNewLocation(Location location) throws Exception {
    return locationsService.addNewLocation(location);
  }

  public Location updateLocation(Integer location_num, String name) {
    return locationsService.updateLocation(location_num, name);
  }

  public Location deleteLocation(Integer location_num) {
    return locationsService.deleteLocation(location_num);
  }


}
