package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.user.ResponseUserDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.user.UserDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper.UserMapper;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.StdUser;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
    public ResponseEntity<ResponseUserDTO> read(@PathVariable String id){
        Optional<StdUser> opUser = service.findById(UUID.fromString(id));

        if (opUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        ResponseUserDTO dto = mapper.toDTO(opUser.get());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid UserDTO dto){
        return service.findById(UUID.fromString(id))
                .map(stdUser -> {
                    stdUser.setName(dto.name());
                    stdUser.setEmail(dto.email());
                    stdUser.setPassword(dto.password());
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

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ResponseUserDTO>>> search(

            @RequestParam(value = "name", required = false)
            String name,
            @RequestParam(value = "email", required = false)
            String email,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "page-size", defaultValue = "10")
            Integer pageSize,
            PagedResourcesAssembler<ResponseUserDTO> assembler
    ){

        Page<StdUser> pageResult = service.search(name, email, page, pageSize);

        Page<ResponseUserDTO> finalDTO = pageResult.map(mapper::toDTO);

        return ResponseEntity.ok(assembler.toModel(finalDTO));
    }
}
