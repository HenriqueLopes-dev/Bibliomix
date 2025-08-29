package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.AuthorDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDTO(Author author);

    Author toEntity(AuthorDTO dto);
}
