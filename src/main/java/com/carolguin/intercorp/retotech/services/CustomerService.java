package com.carolguin.intercorp.retotech.services;

import com.carolguin.intercorp.retotech.dto.Customer;
import com.carolguin.intercorp.retotech.dto.CustomerAllData;
import com.carolguin.intercorp.retotech.dto.CustomerStats;
import com.carolguin.intercorp.retotech.model.CustomerJPA;
import com.carolguin.intercorp.retotech.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

  @Value("${life.expectation.in.years}")
  private double lifeExpectationInYears;

  @Autowired
  private CustomerRepository customerRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

  public Customer create(Customer customer) {
    CustomerJPA customerJPA = new CustomerJPA(customer);
    CustomerJPA customerSaved = customerRepository.save(customerJPA);
    LOGGER.info("Customer ({} - {} {}) created successfully", customerSaved.getId(), customerSaved.getFirstName(), customerSaved.getLastName());
    return new Customer(customerSaved);
  }

  public Optional<CustomerStats> getCustomerStats() {
    Double ageAverage = customerRepository.getAgeAverage();
    Double ageStandardDeviation = customerRepository.getAgeStandardDeviation();
    if (ageAverage!=null && ageStandardDeviation!=null) {
      LOGGER.info("Customers' average age -> {}", ageAverage);
      LOGGER.info("Standard deviation of customer ages -> {}", ageStandardDeviation);
      return Optional.of(new CustomerStats(ageAverage, ageStandardDeviation));
    }
    else {
      LOGGER.warn("Unable to calculate customer statistics");
      return Optional.empty();
    }
  }


  public List<CustomerAllData> getAllData(){
    LOGGER.info("Getting all customers data");
    List<CustomerJPA> customerJPAList = (List<CustomerJPA>) customerRepository.findAll();
    List<CustomerAllData> customers = customerJPAList.stream().map(cJPA -> new CustomerAllData(cJPA, lifeExpectationInYears)).collect(Collectors.toList());
    return customers;
  }

}
