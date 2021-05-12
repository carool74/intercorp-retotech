package com.carolguin.intercorp.retotech.dto;

import com.carolguin.intercorp.retotech.model.CustomerJPA;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Customer {

  @NotBlank(message = "First name can't be blank")
  @ApiModelProperty(name = "firstName", dataType = "String", example = "Carolina", notes = "Customer's first name")
  private String firstName;

  @NotBlank(message = "Last name can't be blank")
  @ApiModelProperty(name = "lastName", dataType = "String", example = "Olguín", notes = "Customer's last name")
  private String lastName;

  @NotNull
  @Min(value = 0, message = "Age should be greater than 0")
  @Max(value = 120, message = "Age should be less than 120")
  @ApiModelProperty(name = "age", dataType = "int", example = "46", notes = "Customer's age")
  private int age;

  @NotNull
  @JsonFormat(pattern="yyyy-MM-dd")
  @ApiModelProperty(name = "birthday", dataType = "Date", example = "1974-11-07", notes = "Customer's birthday")
  private Date birthday;

  public Customer(){}

  public Customer(CustomerJPA jpa){
    this.firstName = jpa.getFirstName();
    this.lastName = jpa.getLastName();
    this.age = jpa.getAge();
    this.birthday = jpa.getBirthday();
  }

  public Customer(String firstName, String lastName, int age, Date birthday){
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.birthday = birthday;
  }

}
