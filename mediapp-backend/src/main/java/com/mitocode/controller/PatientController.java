package com.mitocode.controller;

import com.mitocode.dto.PatientDTO;
import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
//@RequestMapping("${patient.controller.path}")
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    //@Autowired
    private final IPatientService service; // = new PatientServiceImpl();
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @Value("${patient.controller.path}")
    private String pathUrl;

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

    @GetMapping("/hateoas/{id}")
    public EntityModel<PatientDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        EntityModel<PatientDTO> resource = EntityModel.of(convertToDto(service.findById(id)));

        // Generar link informativo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));

        resource.add(link1.withRel("patient-self-info"));

        return resource;
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
