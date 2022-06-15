package com.hvacparts.parts.dao;

import java.util.List;
import java.util.Optional;
import com.hvacparts.parts.entity.Part;

public interface PartsDao {

  List<Part> showAllParts();

  Optional<Part> getSpecificPart(String part_num);

  Optional<Part> save(Part part);

  void delete(Part part);
}
