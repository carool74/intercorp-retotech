package com.carolguin.intercorp.retotech.repositories;

import com.carolguin.intercorp.retotech.model.CustomerJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerJPA, Long> {

  @Query(value = "SELECT AVG(c.age) from customer c", nativeQuery = true)
  Double getAgeAverage();

  @Query(value = "SELECT STDDEV_POP(c.age) from customer c", nativeQuery = true)
  Double getAgeStandardDeviation();

}
