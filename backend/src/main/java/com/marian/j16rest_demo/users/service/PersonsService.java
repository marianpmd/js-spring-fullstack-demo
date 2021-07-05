package com.marian.j16rest_demo.users.service;


import com.marian.j16rest_demo.users.DTO.PersonDTO;
import com.marian.j16rest_demo.users.DTO.RoleDTO;
import com.marian.j16rest_demo.users.controller.PersonNotFoundException;
import com.marian.j16rest_demo.users.entity.PersonEntity;
import com.marian.j16rest_demo.users.entity.RolesEntity;
import com.marian.j16rest_demo.users.repository.PersonsRepository;
import com.marian.j16rest_demo.users.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonsService {

    private final PersonsRepository personsRepository;
    private final RolesRepository rolesRepository;

    @Transactional(readOnly = true)
    public List<PersonDTO> getAllPersons(){

        return personsRepository.findAll()
                .stream()
                .map(personEntity -> PersonDTO.builder()
                        .address(personEntity.getAddress())
                        .name(personEntity.getName())
                        .roles(mapToRolesDTO(personEntity.getRoles() , personEntity))
                        .build()).collect(Collectors.toList());
    }

    private List<RoleDTO> mapToRolesDTO(List<RolesEntity> roles, PersonEntity personEntity) {
        return roles.stream()
                .map(rolesEntity -> RoleDTO.builder()
                        .name(rolesEntity.getName())
                        .salary(rolesEntity.getSalary())
                        .build()).collect(Collectors.toList());
    }


    @Transactional
    public void addPerson(PersonDTO personDTO) {

        List<RolesEntity> collect = personDTO.roles()
                .stream()
                .map(roleDTO -> new RolesEntity(roleDTO.name(), roleDTO.salary()))
                .collect(Collectors.toList());

        PersonEntity personEntity = new PersonEntity(personDTO.name(),personDTO.address(),collect);
        collect.forEach(elem->{
            elem.setPerson(personEntity);
        });
        rolesRepository.saveAll(collect);
        personsRepository.save(personEntity);
    }

   @Transactional
    public PersonDTO putPerson(PersonDTO personDTO) {
        PersonEntity personEntity = personsRepository.findByName(personDTO.name())
                .orElseThrow(()->new PersonNotFoundException("Person not found!"));

        List<RolesEntity> entities = personDTO.roles().stream()
                .map(dtoRole -> new RolesEntity(dtoRole.name(), dtoRole.salary()))
                .peek(rolesEntity -> rolesEntity.setPerson(personEntity))
                .collect(Collectors.toList());

        rolesRepository.saveAll(entities);

        personEntity.setRoles(entities);
        personEntity.setAddress(personDTO.address());
        personsRepository.save(personEntity);

        return  personDTO;

    }
}
