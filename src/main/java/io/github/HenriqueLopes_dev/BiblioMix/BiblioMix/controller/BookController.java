package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.BookDTO;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.mapper.BookMapper;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Book;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientWebSecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

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
}
