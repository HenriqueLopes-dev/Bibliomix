package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.book.BookDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.book.ResponseBookDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper.BookMapper;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Book;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.BookStatus;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController implements GenericController{

    private final BookService service;
    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid BookDTO dto){
        Book book = mapper.toEntity(dto);
        service.save(book);

        URI uri = generateHeaderLocation(book.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseBookDTO> read(@PathVariable String id){

        Optional<Book> opBook = service.findById(UUID.fromString(id));

        if (opBook.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        ResponseBookDTO dto = mapper.toDTO(opBook.get());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid BookDTO dto){

        Book entity = mapper.toEntity(dto);

        return service.findById(UUID.fromString(id))
                .map(book -> {
                    book.setTitle(entity.getTitle());
                    book.setIsbn(entity.getIsbn());
                    book.setAuthor(entity.getAuthor());
                    book.setOwner(entity.getOwner());
                    book.setPublishedDate(entity.getPublishedDate());
                    book.setStatus(entity.getStatus());

                    service.update(book);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){

        Optional<Book> opBook = service.findById(UUID.fromString(id));

        if (opBook.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.delete(opBook.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ResponseBookDTO>>> search(

            @RequestParam(value = "title", required = false)
            String title,
            @RequestParam(value = "published-date", required = false)
            LocalDate publishedDate,
            @RequestParam(value = "status", required = false)
            BookStatus status,
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "page-size", defaultValue = "10")
            Integer pageSize,
            PagedResourcesAssembler<ResponseBookDTO> assembler
    ){

        Page<Book> pageResult = service.search(title, publishedDate, status, isbn, page, pageSize);

        Page<ResponseBookDTO> finalDTO = pageResult.map(mapper::toDTO);

        return ResponseEntity.ok(assembler.toModel(finalDTO));
    }


}
