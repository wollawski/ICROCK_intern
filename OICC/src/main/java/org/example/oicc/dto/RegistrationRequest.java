package org.example.oicc.dto;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class RegistrationRequest {
    @NotBlank private String username;
    @NotBlank @Email private String email;
    @NotBlank @Size(min=6) private String password;
    private String nickname;
}
