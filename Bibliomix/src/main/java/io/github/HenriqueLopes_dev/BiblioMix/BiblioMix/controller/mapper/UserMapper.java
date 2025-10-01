package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.user.ResponseUserDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.user.UserDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = ResumeBookMapper.class)
public abstract class UserMapper {

    @Autowired
    ResumeBookMapper resumeBookMapper;

    public abstract StdUser toEntity(UserDTO dto);

    @Mapping(target = "books", expression = "java( user.getBooks().stream().map(resumeBookMapper::toDTO).toList() )")
    public abstract ResponseUserDTO toDTO(StdUser user);
}
