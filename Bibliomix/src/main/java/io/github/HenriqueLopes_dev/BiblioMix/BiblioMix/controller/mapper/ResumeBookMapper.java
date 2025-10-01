package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.book.ResumeBookDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ResumeBookMapper {

    public abstract ResumeBookDTO toDTO(Book book);
}
