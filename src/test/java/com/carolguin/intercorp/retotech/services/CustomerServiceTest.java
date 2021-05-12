package com.carolguin.intercorp.retotech.services;

import com.carolguin.intercorp.retotech.dto.Customer;
import com.carolguin.intercorp.retotech.dto.CustomerAllData;
import com.carolguin.intercorp.retotech.model.CustomerJPA;
import com.carolguin.intercorp.retotech.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

  @InjectMocks
  private CustomerService customerService;

  @Mock
  private CustomerRepository customerRepository;

  @Test
  public void shouldReturnAllCustomersData() {
    List<CustomerJPA> customerList = new ArrayList<>();
    Customer customer1 = new Customer("Carolina","Olguín",46, (new GregorianCalendar(1974, Calendar.NOVEMBER, 7).getTime()));
    Customer customer2 = new Customer("Daniel","Rodriguez",51, (new GregorianCalendar(1970, Calendar.FEBRUARY, 7).getTime()));

    customerList.add(new CustomerJPA(customer1));
    customerList.add(new CustomerJPA(customer2));

    Mockito.when(customerRepository.findAll()).thenReturn(customerList);

    double lifeExpectationInYears = 78.46;
    int lifeExpectationInDays = (int)(lifeExpectationInYears * 365);
    Calendar death1 = Calendar.getInstance();
    death1.setTime(customer1.getBirthday());
    death1.add(Calendar.DAY_OF_YEAR, lifeExpectationInDays);

    List<CustomerAllData> customers = customerService.getAllData();
    assertEquals(2, customers.size());
    assertEquals("Carolina", customers.get(0).getFirstName());
    assertEquals("Olguín", customers.get(0).getLastName());
    assertEquals(46, customers.get(0).getAge());
    assertEquals(death1, customers.get(0).getProbableDeathDate());
  }
}
