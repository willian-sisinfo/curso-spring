package br.com.udemy.controller;

import br.com.udemy.data.vo.PersonVO;
import br.com.udemy.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// @CrossOrigin
@Api(tags= "PersonEndpoint")
@RestController
@RequestMapping(value = "/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PagedResourcesAssembler<PersonVO> assembler;

    @ApiOperation(value = "Find all people in database", produces = "Json", notes = "Muahaha")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
        Page<PersonVO> vos = service.findAll(pageable);
        vos
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
                );
        return new ResponseEntity<>(assembler.toModel(vos), HttpStatus.OK);
    }

    @ApiOperation(value = "Find all people in database by the first name", produces = "Json", notes = "Muahaha")
    @GetMapping(value = "/findPersonByName/{firstName}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findPersonByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
        Page<PersonVO> vos = service.findPersonByName(firstName, pageable);
        vos
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
                );
        return new ResponseEntity<>(assembler.toModel(vos), HttpStatus.OK);
    }
    //@CrossOrigin(origins = {"http://localhost:8080"})
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO findById(@PathVariable(value="id") Long id)  {
        PersonVO vo = service.findById(id);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person)  {
        PersonVO vo = service.create(person);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO update(@RequestBody PersonVO person)  {
        PersonVO vo = service.update(person);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value="id") Long id)  {
         service.delete(id);
         return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Disable a person by id")
    @PatchMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO disablePerson(@PathVariable(value="id") Long id)  {
        PersonVO vo = service.disbalePerson(id);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }


}
