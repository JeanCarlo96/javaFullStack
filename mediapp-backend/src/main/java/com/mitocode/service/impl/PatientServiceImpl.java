package com.mitocode.service.impl;

import com.mitocode.model.Patient;
import com.mitocode.repo.IPatientRepo;
import com.mitocode.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {

    //@Autowired
    private final IPatientRepo repo; // = new PatientRepoImpl();

    @Override
    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public Patient updade(Integer id, Patient patient) {
        // Valdiación de ID
        patient.setIdPatient(id);
        return repo.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return repo.findAll();
    }

    @Override
    public Patient findById(Integer id) {
        return repo.findById(id).orElse(new Patient());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    /*public PatientServiceImpl(IPatientRepo repo) {
        this.repo = repo;
    }*/

    /*
    @Override
    public Patient findByIdAndValidate(int id) {
        if(id > 0){
            return repo.findById(id);
        }
        return new Patient();
    }
     */

}
