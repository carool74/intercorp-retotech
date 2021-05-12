package com.carolguin.intercorp.retotech.controller;

import com.carolguin.intercorp.retotech.dto.Customer;
import com.carolguin.intercorp.retotech.dto.CustomerAllData;
import com.carolguin.intercorp.retotech.dto.CustomerStats;
import com.carolguin.intercorp.retotech.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "Customer")
@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;


  @ApiOperation(value = "Creates a new customer", response= Customer.class)
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Created"),
      @ApiResponse(code = 400, message = "Bad Request"),
  })
  @PostMapping(value = "/creacliente")
  public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
    return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
  }


  @ApiOperation(value = "Get customer statistics")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 400, message = "Bad Request"),
  })
  @GetMapping(value="/kpideclientes")
  public ResponseEntity<CustomerStats> getCustomerStats(){
    Optional<CustomerStats> customerStatsOptional = customerService.getCustomerStats();
    if (!customerStatsOptional.isPresent()){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(customerStatsOptional.get());
  }


  @ApiOperation(value = "Get all customers data")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Bad Request"),
  })
  @GetMapping(value="/listclientes")
  public ResponseEntity<List<CustomerAllData>> getAll(){
    return ResponseEntity.ok(customerService.getAllData());
  }

}
