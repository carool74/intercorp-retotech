package com.carolguin.intercorp.retotech.repositories;

import com.carolguin.intercorp.retotech.dto.CustomerStats;
import com.carolguin.intercorp.retotech.model.CustomerJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerJPA, Long> {

  @Query(value = "SELECT AVG(c.age) as ageAverage, STDDEV_POP(c.age) as ageStandardDeviation FROM customer c", nativeQuery = true)
  Optional<CustomerStats> getCustomerStats();

}
