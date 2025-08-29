package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto;

import java.time.LocalDate;

public record AuthorDTO(String name, String nationality, LocalDate dateOfBirth) {
}
