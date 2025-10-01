package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.book;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;

import java.time.LocalDate;
import java.util.UUID;

public record BookDTO(
        @NotBlank(message = "Campo titulo é obrigatório!")
        @Size(min = 1,max = 255, message = "Campo titulo deve ter entre 1 e 255 caracteres!")
        String title,
        @NotNull(message = "Campo Data de Publicacao é obrigatório!")
        @PastOrPresent(message = "Data de publicacao não pode estar no futuro!")
        LocalDate publishedDate,
        @NotNull(message = "Campo status é obrigatório!")
        BookStatus status,
        @ISBN(message = "Isbn fornecido é inválido!")
        @Size(min = 9, max = 25, message = "Campo isbn deve ter entre 9 e 25 caracteres!")
        String isbn,
        @NotNull(message = "Campo autor é obrigatório!")
        UUID idAuthor,
        @NotNull(message = "Campo dono é obrigatório!")
        UUID idOwner
) {
}
