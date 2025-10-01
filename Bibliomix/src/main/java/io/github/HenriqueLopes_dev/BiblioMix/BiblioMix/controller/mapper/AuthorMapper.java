package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.author.AuthorDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.author.ResponseAuthorDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = ResumeBookMapper.class)
public abstract class AuthorMapper {

    @Autowired
    ResumeBookMapper resumeBookMapper;

    public abstract Author toEntity(AuthorDTO dto);

    @Mapping(target = "books", expression = "java( author.getBooks().stream().map(resumeBookMapper::toDTO).toList() )")
    public abstract ResponseAuthorDTO toDTO(Author author);

}
