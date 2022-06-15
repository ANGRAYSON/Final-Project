package com.hvacparts.parts.dao;

import java.util.List;
import java.util.Optional;
import com.hvacparts.parts.entity.Location;

public interface LocationsDao {

  List<Location> showAllLocations();

  Optional<Location> getSpecificLocation(Integer location_num);

  Optional<Location> save(Location location);

  void delete(Location location);

}
