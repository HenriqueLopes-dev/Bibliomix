package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorDTO(
        @NotBlank(message = "Campo nome obrigatório!")
        @Size(min = 2, max = 100, message = "Campo nome deve ter entre 2 a 100 caracteres!")
        String name,
        @NotBlank(message = "Campo nacionalidade obrigatório!")
        @Size(min = 2, max = 100, message = "Campo nacionalidade deve ter entre 2 a 100 caracteres!")
        String nationality,
        @Past(message = "Não é possivel nascer no futuro!")
        LocalDate dateOfBirth
) {
}
