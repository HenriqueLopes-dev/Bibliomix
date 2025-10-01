package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.user;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.book.ResumeBookDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ResponseUserDTO(
        UUID id,
        String name,
        String email,
        BigDecimal balance,
        List<ResumeBookDTO> books
) {
}
