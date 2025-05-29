package com.fbr.ecommerce.repository;

import com.fbr.ecommerce.entities.BillingAddresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddresEntity, Long> {
}
