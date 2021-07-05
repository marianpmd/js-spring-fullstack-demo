package com.marian.j16rest_demo.users.repository;

import com.marian.j16rest_demo.users.entity.PersonEntity;
import com.marian.j16rest_demo.users.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
}
