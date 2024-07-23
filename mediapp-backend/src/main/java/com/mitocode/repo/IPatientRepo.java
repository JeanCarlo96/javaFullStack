package com.mitocode.repo;

import com.mitocode.model.Patient;

public interface IPatientRepo {

    Patient findById(Integer id);

}
