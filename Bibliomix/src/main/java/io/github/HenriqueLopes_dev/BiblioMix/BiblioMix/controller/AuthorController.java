package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.author.AuthorDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.author.ResponseAuthorDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper.AuthorMapper;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Author;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
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
    public ResponseEntity<ResponseAuthorDTO> read(@PathVariable String id){
        Optional<Author> opAuthor = service.findById(UUID.fromString(id));

        if (opAuthor.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        ResponseAuthorDTO dto = mapper.toDTO(opAuthor.get());

        opAuthor.get().getBooks().forEach(book ->
                System.out.println("Book: " + book.getId() + " - " + book.getTitle())
        );

        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid AuthorDTO dto){
        Author entity = mapper.toEntity(dto);
        return service.findById(UUID.fromString(id))
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

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ResponseAuthorDTO>>> search(

            @RequestParam(value = "name", required = false)
            String name,
            @RequestParam(value = "nationality", required = false)
            String nationality,
            @RequestParam(value = "date-of-birth", required = false)
            LocalDate dateOfBirth,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "page-size", defaultValue = "10")
            Integer pageSize,
            PagedResourcesAssembler<ResponseAuthorDTO> assembler
    ){

        Page<Author> pageResult = service.search(name, nationality, dateOfBirth, page, pageSize);

        Page<ResponseAuthorDTO> finalDTO = pageResult.map(mapper::toDTO);

        return ResponseEntity.ok(assembler.toModel(finalDTO));
    }

}
