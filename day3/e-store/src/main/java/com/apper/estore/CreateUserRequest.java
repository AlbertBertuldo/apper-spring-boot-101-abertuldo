package com.apper.estore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    //@NotBlank - This annotation is typically used for validating that a string value is not null and not empty
    //@Email - This annotation is used to validate that a string value represents a valid email address.

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "password must be at least 8 characters")
    private String password;

    //@JSON Property - It helps in converting JSON data to Java objects and vice versa.

    @JsonProperty("first_name")
    @NotBlank(message = "first_name is required")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    @NotBlank(message = "last_name is required")
    private String lastName;

    @JsonProperty("birth_date")
    @NotBlank(message = "birth_date is required")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "birth_date must be YYYY-MM-DD")  //Coach Orvyl's implementation
    //@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")  // My implementation of birthdate format
    private String birthDate;
}