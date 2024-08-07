package com.mitocode.service;

import com.mitocode.model.Patient;

import java.util.List;

public interface IPatientService {
    Patient save(Patient patient);
    Patient updade(Integer id, Patient patient);
    List<Patient> findAll();
    Patient findById(Integer id);
    void delete(Integer id);
    //Patient findByIdAndValidate(int id);

}
