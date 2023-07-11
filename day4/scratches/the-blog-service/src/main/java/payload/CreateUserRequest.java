package payload;

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
    @Size(min = 5, message = "password must be at least 5 characters")
    private String password;

    //@JSON Property - It helps in converting JSON data to Java objects and vice versa.

    @JsonProperty("name")
    @NotBlank(message = "name is required")
    private String name;

}
