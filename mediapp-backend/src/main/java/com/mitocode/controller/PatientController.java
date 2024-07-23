package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${patient.controller.path}")
public class PatientController {

    //@Autowired
    private final IPatientService service; // = new PatientServiceImpl();

    @GetMapping
    public Patient findById(){
        return service.findByIdAndValidate(5);
    }

}
