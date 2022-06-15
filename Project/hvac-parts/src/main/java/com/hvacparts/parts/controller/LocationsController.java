package com.hvacparts.parts.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hvacparts.parts.entity.Location;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(info = @Info(title = "Locations Controller"))
@Tag(name = "Locations")
public interface LocationsController {

  @Operation(
      summary = "Show all locations",
      description = "Returns a list of all HVAC service part locations",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of all locations is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Location.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No locations were found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/locations", method = RequestMethod.GET)
  List<Location> showAllLocations();
  
  //API Documentation
  @Operation(
      summary = "Show a specific location",
      description = "Returns a location based on unique ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The specific location was returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Location.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No location with that ID could be found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/locations/{location_num}", method = RequestMethod.GET)
  Location showSpecificLocation(@PathVariable Integer location_num);
  
  //API Documentation
  @Operation(
      summary = "Add new location",
      description = "Create a new location",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The location was created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Location.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Missing component of location",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/locations", method = RequestMethod.POST)
  Location addNewLocation(@RequestBody Location location) throws Exception;
  
  //API Documentation
  @Operation(
      summary = "Update an existing location",
      description = "Update an existing location base on its unique ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The location was updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Location.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Missing component of location, or no location found with that ID",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/locations/{location_num}", method = RequestMethod.PUT)
  Location updateLocation(@PathVariable Integer location_num, String name);
  
  @Operation(
      summary = "Delete a location",
      description = "Delete a location based on ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The location was deleted successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Location.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No location with that ID found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/locations/{location_num}", method = RequestMethod.DELETE)
  Location deleteLocation(@PathVariable Integer location_num);
}
