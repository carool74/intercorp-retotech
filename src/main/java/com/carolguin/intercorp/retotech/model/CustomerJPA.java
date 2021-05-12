package com.carolguin.intercorp.retotech.model;

import com.carolguin.intercorp.retotech.dto.Customer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "customer")
public class CustomerJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String firstName;
  private String lastName;
  private int age;
  private Date birthday;

  public CustomerJPA(Customer customer){
    this.firstName = customer.getFirstName();
    this.lastName = customer.getLastName();
    this.age = customer.getAge();
    this.birthday = customer.getBirthday();
  }

}
