package com.mitocode.controller;

import com.mitocode.dto.PatientDTO;
import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${patient.controller.path}")
public class PatientController {

    //@Autowired
    private final IPatientService service; // = new PatientServiceImpl();
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll() {
        //List<PatientDTO> list = service.findAll().stream().map(p -> new PatientDTO(p.getIdPatient(), p.getFirstName(), p.getLastName(), p.getDni(), p.getAddress(), p.getPhone(), p.getEmail())).toList();
        List<PatientDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id){
        Patient obj = service.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PatientDTO dto){
        Patient obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody PatientDTO dto){
        Patient obj = service.updade(id, convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*@GetMapping
    public Patient findById(){
        return service.findByIdAndValidate(5);
    }*/

    private PatientDTO convertToDto(Patient patient){
        return mapper.map(patient, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO dto){
        return mapper.map(dto, Patient.class);
    }

}
