package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.BookStatus;

import java.time.LocalDate;

public record BookDTO(String title, LocalDate publishedDate, BookStatus status, String isbn) {
}
