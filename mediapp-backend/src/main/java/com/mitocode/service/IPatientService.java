package com.mitocode.service;

import com.mitocode.model.Patient;

public interface IPatientService {

    Patient findByIdAndValidate(int id);

}
