package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @Size(min = 2, max = 100, message = "O nome precisa conter entre 2 e 100 caracteres!")
        @NotBlank(message = "Campo nome obrigatório!")
        String name,
        @Email(message = "Email invalido!")
        @NotBlank(message = "Campo email obrigatório!")
        @Pattern(regexp = "^[^A-Z]+$", message = "Um email válido não possui letras maiúsculas!")
        String email,
        @Size(min = 8, max = 100, message = "Entre 8 e 100 caracteres.")
        @Pattern(regexp = "^(?=.*[a-z]).+$", message = "Pelo menos 1 caractere minúsculo.")
        @Pattern(regexp = "^(?=.*[A-Z]).+$", message = "Pelo menos 1 caractere maiúsculo.")
        @Pattern(regexp = "^(?=.*[^\\w\\d\\s]).+$", message = "Pelo menos 1 caractere especial.")
        @Pattern(regexp = "^(?=.*[\\d]).+$", message = "Pelo menos 1 digito.")
        String password
){
}
