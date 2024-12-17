package com.mitocode.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private Integer idPatient;

    @NotNull
    //@NotEmpty
    //@NotBlank
    @Size(min = 3, max = 70, message = "{firstname.size}")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 70, message = "{lastname.size}")
    private String lastName;

    @NotNull
    private String dni;

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp="\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}")
    private String phone;

    @Email
    private String email;

}
