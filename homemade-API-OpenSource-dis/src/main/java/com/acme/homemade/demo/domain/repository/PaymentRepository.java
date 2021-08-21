package com.acme.homemade.demo.domain.repository;


import com.acme.homemade.demo.domain.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    Page<Payment> findById (Long Id, Pageable pageable);

    Page<Payment> findByUserNoChefId (Long userNoChefId, Pageable pageable);

    Optional<Payment> findByIdAndUserNoChefId (Long Id, Long userNoChefId);
}
