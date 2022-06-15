package com.hvacparts.parts.controller;

import java.util.List;
import javax.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hvacparts.parts.entity.Inventory;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(info = @Info(title = "Inventory Controller"))
@Tag(name = "Inventory")
public interface InventoryController {
  
  @Operation(
      summary = "Show all Inventory",
      description = "Returns a list of all parts, their stock, and their location",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of all inventories is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Inventory.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No inventories were found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/inventory/all", method = RequestMethod.GET)
  List<Inventory> getAllInventory();
  
  @Operation(
      summary = "Show specific part Inventory",
      description = "Returns stock and locations for specific part",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A inventory for a specific part is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Inventory.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No invnetories were found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/inventory/part/{part_num}", method = RequestMethod.GET)
  List<Inventory> getSpecificPartInventory(@Pattern(regexp = "[A-Za-z0-9]*") @PathVariable String part_num);
  
  
  @Operation(
      summary = "Update a specific part's inventory",
      description = "Updates stock of a specific part, at specific location",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Stock successfully updated",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Inventory.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No inventory found with that ID, missing inventroy component",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/inventory/update/{part_num}/{location_num}", method = RequestMethod.PUT)
  Inventory updatePartInventory(@Pattern(regexp = "[A-Za-z0-9]*") @PathVariable String part_num, @PathVariable Integer location_num, Integer stock);
  
  @Operation(
      summary = "Show all inventory in one location",
      description = "Returns all parts, stock in a specific location based on location ID",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of all inventory with specific location is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Inventory.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No inventories were found",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/inventory/location/{location_num}", method = RequestMethod.GET)
  List<Inventory> getInventoryByLocation(@PathVariable Integer location_num);
  
  @Operation(
      summary = "Create new inventory at specific location",
      description = "Creates a new inventory item at a certain location (shop is default)",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "An inventory was successfully created",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Inventory.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Missing inventory component",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/inventory/new/{location_num}", method = RequestMethod.POST)
  Inventory createNewInventoryPartsAtLocation(@PathVariable Integer location_num, Integer stock, String part_num ) throws Exception;
  
  

}
