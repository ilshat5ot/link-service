package ru.sadykov.link.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sadykov.link.common.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
