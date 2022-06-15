package com.hvacparts.parts.service;

import java.util.List;
import com.hvacparts.parts.entity.Location;

public interface LocationsService {

  List<Location> showAllLocations();

  Location getSpecificLocation(Integer location_num);

  Location addNewLocation(Location location) throws Exception;

  Location updateLocation(Integer location_num, String name);

  Location deleteLocation(Integer location_num);

}
