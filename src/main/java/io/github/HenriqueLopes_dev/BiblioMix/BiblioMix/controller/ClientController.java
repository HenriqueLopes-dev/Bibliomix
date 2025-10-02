package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Client;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController implements GenericController{

    private final ClientService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Client client){
        service.save(client);
        URI uri = generateHeaderLocation(client.getId());
        return ResponseEntity.created(uri).build();
    }
}
