package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.service;


import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.model.Client;
import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client save(Client client){
        String encryptedPassword = encoder.encode(client.getClientSecret());
        client.setClientSecret(encryptedPassword);
        return repository.save(client);
    }

    public Optional<Client> findbyClientId(String clientId){
        return repository.findByClientId(clientId);
    }
}
