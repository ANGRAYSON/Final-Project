package com.hvacparts.parts.service;

import java.util.List;
import com.hvacparts.parts.entity.Part;
import com.hvacparts.parts.entity.Type;

public interface PartsService {

  List<Part> showAllParts();

  Part getSpecificPart(String part_num);

  Part addNewPart(Part part);

  Part updatePart(String part_num, String part_name, Type type);

  Part deletePart(String part_num);

}
