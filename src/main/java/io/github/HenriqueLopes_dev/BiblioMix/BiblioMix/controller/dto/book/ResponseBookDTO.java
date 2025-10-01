package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.book;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.BookStatus;

import java.time.LocalDate;
import java.util.UUID;

public record ResponseBookDTO(
        UUID id,
        String title,
        LocalDate publishedDate,
        BookStatus status,
        String isbn,
        UUID idAuthor,
        String nameAuthor,
        UUID idOwner,
        String nameOwner
) {
}
