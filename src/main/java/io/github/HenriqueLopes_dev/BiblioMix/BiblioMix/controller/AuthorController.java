package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.AuthorDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper.AuthorMapper;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorController implements GenericController{

    private final AuthorService service;
    private final AuthorMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid AuthorDTO dto){

        Author author = mapper.toEntity(dto);
        service.save(author);

        URI uri = generateHeaderLocation(author.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> read(@PathVariable String id){
        Optional<Author> opAuthor = service.findById(UUID.fromString(id));

        if (opAuthor.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        AuthorDTO dto = mapper.toDTO(opAuthor.get());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid AuthorDTO dto){
        Author entity = mapper.toEntity(dto);
        return service.findById(entity.getId())
                .map(author -> {

                    author.setName(entity.getName());
                    author.setDateOfBirth(entity.getDateOfBirth());
                    author.setNationality(entity.getNationality());

                    service.update(author);

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        Optional<Author> opAuthor = service.findById(UUID.fromString(id));

        if (opAuthor.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.delete(opAuthor.get());

        return ResponseEntity.noContent().build();
    }

}
