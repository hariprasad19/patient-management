package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import com.pm.patientservice.validator.CreatePatientValidatorGroup;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidatorGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        return new ResponseEntity<>(patientService.createPatient(patientRequestDTO),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable String id){
        return new ResponseEntity<>(patientService.getPatientById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatientById(@PathVariable String id,@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        return new ResponseEntity<>(patientService.updatePatient(UUID.fromString(id),patientRequestDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id){
        patientService.deletePatient(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
