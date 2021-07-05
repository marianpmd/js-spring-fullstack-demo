package com.marian.j16rest_demo;

import com.marian.j16rest_demo.users.entity.PersonEntity;
import com.marian.j16rest_demo.users.entity.RolesEntity;
import com.marian.j16rest_demo.users.repository.PersonsRepository;
import com.marian.j16rest_demo.users.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Component
public class CommandLiner implements CommandLineRunner {

    private final PersonsRepository personsRepository;
    private final RolesRepository rolesRepository;

    @Override
    public void run(String... args) throws Exception {

            RolesEntity rolesEntity = new RolesEntity("role1",123d);
            PersonEntity personEntity = new PersonEntity("Marian","addr1", List.of(rolesEntity));
            rolesEntity.setPerson(personEntity);
            rolesRepository.save(rolesEntity);
            personsRepository.save(personEntity);

            RolesEntity rolesEntity1 = new RolesEntity("role2",1234d);
            PersonEntity personEntity1= new PersonEntity("Alex","addr2", List.of(rolesEntity1));
            rolesEntity1.setPerson(personEntity1);
            rolesRepository.save(rolesEntity1);
            personsRepository.save(personEntity1);

    }
}
