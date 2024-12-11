package com.mitocode.repo;

import com.mitocode.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPatientRepo extends IGenericRepo<Patient, Integer> {

}
