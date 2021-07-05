package com.marian.j16rest_demo.users.repository;

import com.marian.j16rest_demo.users.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<PersonEntity , Long> {
    Optional<PersonEntity> findByNameAndAddress(String name, String address);

    Optional<PersonEntity> findByName(String name);
}
