package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.author;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.book.ResumeBookDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ResponseAuthorDTO(
        UUID id,
        String name,
        String nationality,
        LocalDate dateOfBirth,
        List<ResumeBookDTO> books
) {
}
