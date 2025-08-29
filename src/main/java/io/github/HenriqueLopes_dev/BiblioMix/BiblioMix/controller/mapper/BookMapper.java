package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.BookDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookDTO dto);

    BookDTO toDTO(Book book);
}
