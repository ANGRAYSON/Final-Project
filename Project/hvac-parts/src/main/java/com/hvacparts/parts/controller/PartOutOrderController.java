package com.hvacparts.parts.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hvacparts.parts.entity.PartsOut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@OpenAPIDefinition(info = @Info(title = "Parts Out OrderController"))
@Tag(name = "Parts Out Orders")
public interface PartOutOrderController {
  @Operation(
      summary = "Create a parts out order",
      description = "Returns a created parts out order",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Successfully created a parts out order",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PartsOut.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid reqeust",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Missing parts out component",
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(
                  mediaType = "application/json"))
          
      }
  )
  @RequestMapping(value = "/parts_out", method = RequestMethod.POST)
  PartsOut createPartOutOrder(PartsOut partsOut);

}
