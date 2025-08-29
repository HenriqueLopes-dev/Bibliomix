package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.UserDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper.UserMapper;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController implements GenericController{

    private final UserMapper mapper;
    private final UserService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid UserDTO dto){

        StdUser user = mapper.toEntity(dto);
        service.save(user);
        URI uri = generateHeaderLocation(user.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> read(@PathVariable String id){
        Optional<StdUser> opUser = service.findById(UUID.fromString(id));

        if (opUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        UserDTO dto = mapper.toDTO(opUser.get());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid UserDTO dto){
        StdUser user = mapper.toEntity(dto);
        return service.findById(user.getId())
                .map(stdUser -> {
                    stdUser.setName(user.getName());
                    stdUser.setEmail(user.getEmail());
                    stdUser.setPassword(user.getPassword());
                    service.update(stdUser);

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        Optional<StdUser> opUser = service.findById(UUID.fromString(id));

        if (opUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.delete(opUser.get());

        return ResponseEntity.noContent().build();
    }
}
