package com.carolguin.intercorp.retotech.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStats {

  @ApiModelProperty(name = "ageAverage", dataType = "Double", example = "28.8", notes = "Customers' average age")
  private Double ageAverage;

  @ApiModelProperty(name = "ageStandardDeviation", dataType = "Double", example = "4.3", notes = "Standard deviation of customer ages")
  private Double ageStandardDeviation;

}
