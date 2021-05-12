package com.carolguin.intercorp.retotech.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerStats {

  @ApiModelProperty(name = "ageAverage", dataType = "double", example = "28.8", notes = "Customers' average age")
  private double ageAverage;

  @ApiModelProperty(name = "ageStandardDeviation", dataType = "double", example = "4.3", notes = "Standard deviation of customer ages")
  private double ageStandardDeviation;

}
