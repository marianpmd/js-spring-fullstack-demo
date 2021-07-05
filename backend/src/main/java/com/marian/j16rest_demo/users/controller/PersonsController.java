package com.marian.j16rest_demo.users.controller;

import com.marian.j16rest_demo.users.DTO.PersonDTO;
import com.marian.j16rest_demo.users.service.PersonsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
@CrossOrigin("http://localhost:63343")
@AllArgsConstructor
public class PersonsController {
    private final PersonsService personsService;


    @GetMapping("/get")
    List<PersonDTO> getAllPersons(){
        return personsService.getAllPersons();
    }

    @PostMapping("/post")
    void postPerson(@RequestBody PersonDTO personDTO){
        personsService.addPerson(personDTO);
    }

}
