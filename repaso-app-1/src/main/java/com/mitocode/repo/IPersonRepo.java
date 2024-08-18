package com.mitocode.repo;

import com.mitocode.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepo extends JpaRepository<Person, Integer> {


}
