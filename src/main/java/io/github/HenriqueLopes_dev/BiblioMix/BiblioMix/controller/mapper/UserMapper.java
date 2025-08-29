package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.UserDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    StdUser toEntity(UserDTO dto);

    UserDTO toDTO(StdUser user);
}
