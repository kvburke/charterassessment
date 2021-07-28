package com.charter.assessment.repository;

import com.charter.assessment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {



    @Query(value = "select count(*) from customer c "+
            "where c.first_name = ?1 and c.last_name = ?2", nativeQuery = true )
    Long doesCustomerExist(String firstName, String lastName);


}
