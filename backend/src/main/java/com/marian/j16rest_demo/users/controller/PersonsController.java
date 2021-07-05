package com.marian.j16rest_demo.users.controller;

import com.marian.j16rest_demo.users.DTO.PersonDTO;
import com.marian.j16rest_demo.users.service.PersonsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
@CrossOrigin("http://localhost:63343")
@AllArgsConstructor
public class PersonsController {
    private final PersonsService personsService;
    private final Logger logger = LoggerFactory.getLogger(PersonsController.class);


    @GetMapping("/get")
    List<PersonDTO> getAllPersons(){
        logger.info("Request to getAllPersons()");
        return personsService.getAllPersons();
    }

    @PostMapping("/post")
    void postPerson(@RequestBody PersonDTO personDTO){
        logger.info("Request post " + personDTO.toString());
        personsService.addPerson(personDTO);
    }

    @PutMapping("/put")
    PersonDTO putPerson(@RequestBody PersonDTO personDTO){
        logger.info("Request put " + personDTO);
        return personsService.putPerson(personDTO);


    }

}
