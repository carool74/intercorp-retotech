package com.carolguin.intercorp.retotech.dto;

import com.carolguin.intercorp.retotech.model.CustomerJPA;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Calendar;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerAllData extends Customer{

  @ApiModelProperty(name = "id", dataType = "long", example = "1", notes = "Customer's id")
  private long id;

  @JsonFormat(pattern="yyyy-MM-dd")
  @ApiModelProperty(name = "probableDeathDate", dataType = "Date", example = "2072-11-24", notes = "Customer's probable date of death")
  private Date probableDeathDate;

  public CustomerAllData(){ super(); }

  public CustomerAllData(CustomerJPA customerJPA, double lifeExpectationInYears){
    super(customerJPA);
    this.id = customerJPA.getId();
    calculateProbableDeathDate(lifeExpectationInYears);
  }

  private void calculateProbableDeathDate(double lifeExpectationInYears) {
    int lifeExpectationInDays = (int)(lifeExpectationInYears * 365);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(this.getBirthday());
    calendar.add(Calendar.DAY_OF_YEAR, lifeExpectationInDays);
    this.probableDeathDate = calendar.getTime();
  }
}
