package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PatientResponseDTO> postPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        return new ResponseEntity<>(patientService.createPatient(patientRequestDTO),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable String id){
        return new ResponseEntity<>(patientService.getPatientById(id),HttpStatus.OK);
    }
}
