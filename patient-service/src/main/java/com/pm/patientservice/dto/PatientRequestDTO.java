package com.pm.patientservice.dto;

import com.pm.patientservice.validator.CreatePatientValidatorGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max=100,message="Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message="Email is required")
    @Email(message="Email should be valid")
    private String email;

    @NotBlank(message="Address is required")
    private String address;

    @NotBlank(message="DOB is required")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidatorGroup.class, message = "Registered Date is required")
    private String registeredDate;

}
