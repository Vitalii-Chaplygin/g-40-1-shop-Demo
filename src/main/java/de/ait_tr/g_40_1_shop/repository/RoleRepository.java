package de.ait_tr.g_40_1_shop.repository;

import de.ait_tr.g_40_1_shop.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findRoleByTitle(String title);
}
