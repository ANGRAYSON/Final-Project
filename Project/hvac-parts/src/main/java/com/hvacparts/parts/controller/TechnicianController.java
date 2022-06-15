package com.hvacparts.parts.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hvacparts.parts.entity.ActiveStatus;
import com.hvacparts.parts.entity.Technician;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(info = @Info(title = "Technician Controller"))
@Tag(name = "Technicians")
public interface TechnicianController {
  
  @Operation(
      summary = "Show all technicians",
      description = "Returns a list of all HVAC service techs",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of all technicians is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Technician.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No technicians were found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/technicians", method = RequestMethod.GET)
  List<Technician> showAllTechnicians();
  
  @Operation(
      summary = "Show a specific technician",
      description = "Returns a technician based on unique ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The specific technician was returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Technician.class))),
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
  @RequestMapping(value = "/technicians/{employee_num}", method = RequestMethod.GET)
  Technician showSpecificTech(@PathVariable Integer employee_num);
  

  @Operation(
      summary = "Add new technician",
      description = "Create a new technician",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The technician was created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Technician.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Missing component of technician",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/technicians", method = RequestMethod.POST)
  Technician addNewTechnician(@RequestBody Technician tech);
  

  @Operation(
      summary = "Update an existing technician",
      description = "Update an existing technician base on its unique ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The technician was updated successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Technician.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Missing component of technician, or no technician found with that ID",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/technicians/{employee_num}", method = RequestMethod.PUT)
  Technician updateTechnician(@PathVariable Integer employee_num, String first_name, String last_name, ActiveStatus status);
  
}
