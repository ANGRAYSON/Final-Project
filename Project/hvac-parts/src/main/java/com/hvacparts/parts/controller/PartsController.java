package com.hvacparts.parts.controller;

import java.util.List;
import javax.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hvacparts.parts.entity.Part;
import com.hvacparts.parts.entity.Type;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@OpenAPIDefinition(info = @Info(title = "Parts Controller"))
@Tag(name = "Parts")
public interface PartsController {
  
  @Operation(
      summary = "Show all parts",
      description = "Returns all HVAC service parts",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of all parts is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Part.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No parts were found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/parts", method = RequestMethod.GET)
  List<Part> showAllParts();
  
  //API Documentation
  @Operation(
      summary = "Show a specific part",
      description = "Returns part based on unique ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The specific part was returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Part.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No part with that ID could be found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/parts/{part_num}", method = RequestMethod.GET)
  Part showSpecificPart(@Pattern(regexp = "[A-Za-z0-9]*")@PathVariable String part_num) throws Exception;
  
  //API Documentation
  @Operation(
      summary = "Add new part",
      description = "Create a new part in HVAC service parts",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The part was created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Part.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Missing component of part",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/parts", method = RequestMethod.POST)
  Part addNewPart(@RequestBody Part part);
  
  //API Documentation
  @Operation(
      summary = "Update an existing part",
      description = "Update an existing part base on its unique ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The part was updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Part.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Missing component of part, or no part found with that ID",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/parts/{part_num}", method = RequestMethod.PUT)
  Part updatePart(@Pattern(regexp = "[A-Za-z0-9]*")@PathVariable String part_num, String part_name, Type type);
  
  @Operation(
      summary = "Delete a part",
      description = "Delete a part in HVAC service parts",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The part was deleted successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Part.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No part with that ID found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/parts/{part_num}", method = RequestMethod.DELETE)
  Part deletePart(@Pattern(regexp = "[A-Za-z0-9]*")@PathVariable String part_num);
  

}
