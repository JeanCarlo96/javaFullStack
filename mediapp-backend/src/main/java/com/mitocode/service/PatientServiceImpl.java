package com.mitocode.service;

import com.mitocode.model.Patient;
import com.mitocode.repo.IPatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService{

    //@Autowired
    private final IPatientRepo repo; // = new PatientRepoImpl();

    /*public PatientServiceImpl(IPatientRepo repo) {
        this.repo = repo;
    }*/

    @Override
    public Patient findByIdAndValidate(int id) {
        if(id > 0){
            return repo.findById(id);
        }
        return new Patient();
    }

}
