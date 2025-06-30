package de.ait_tr.g_40_1_shop.repository;

import de.ait_tr.g_40_1_shop.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer,Long>{
}
