package com.mitocode.Service.impl;

import com.mitocode.Service.IPersonService;
import com.mitocode.model.Person;
import com.mitocode.repo.IPersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements IPersonService {


    private final IPersonRepo repo;

    @Override
    public Person save(Person person) {
        return repo.save(person);
    }

    @Override
    public Person update(Integer id, Person person) {
        person.setId(id);
        return repo.save(person);
    }

    @Override
    public List<Person> findAll() {
        return repo.findAll();
    }

    @Override
    public Person findById(Integer id) {
        return repo.findById(id).orElse(new Person());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
