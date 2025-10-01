package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.author.AuthorDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.book.BookDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.book.ResponseBookDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.user.UserDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Book;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.AuthorRepository;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {AuthorDTO.class, UserDTO.class})
public abstract class BookMapper {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    UserRepository userRepository;

    @Mapping(target = "author", expression = "java( authorRepository.findById(dto.idAuthor()).orElse(null) )")
    @Mapping(target = "owner", expression = "java( userRepository.findById(dto.idOwner()).orElse(null) )")
    public abstract Book toEntity(BookDTO dto);

    @Mapping(target = "idAuthor", expression = "java( book.getAuthor().getId() )")
    @Mapping(target = "nameAuthor", expression = "java( book.getAuthor().getName() )")
    @Mapping(target = "idOwner", expression = "java( book.getOwner().getId() )")
    @Mapping(target = "nameOwner", expression = "java( book.getOwner().getName() )")
    public abstract ResponseBookDTO toDTO(Book book);
}
