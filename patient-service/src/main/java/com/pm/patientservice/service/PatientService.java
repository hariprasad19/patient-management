package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients=patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOS=patients.stream().map(patient -> patientMapper.toDTO(patient)).toList();
        return patientResponseDTOS;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A Patient with this email already exists" + patientRequestDTO.getEmail());
        }
        Patient newPatient= patientRepository.save(patientMapper.toModel(patientRequestDTO));
        return patientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO getPatientById(String id){
        Patient patient=patientRepository.findById(UUID.fromString(id)).orElseThrow(()-> new NoSuchElementException("not found"));
        return patientMapper.toDTO(patient);
    }

}
