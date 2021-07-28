package com.charter.assessment.repository;

import com.charter.assessment.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(value = "select price from purchase p "+
            "inner join customer c on p.customer_id = c.id "+
            "where c.first_name = ?1 and c.last_name = ?2", nativeQuery = true)
    List<Long> findPurchasesByName(String firstName, String lastName);
}
