package com.mitocode.controller;

import com.mitocode.Service.IPersonService;
import com.mitocode.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${person.controller.path}")
@RequiredArgsConstructor
public class PersonController {

    private final IPersonService service;

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        List<Person> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Integer id) {
        Person obj = service.findById(id);

        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
       Person obj = service.save(person);

       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
       return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable("id") Integer id, @RequestBody Person person) {
        Person obj = service.update(id, person);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
       service.delete(id);
       return ResponseEntity.noContent().build();
    }

}