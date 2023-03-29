package br.com.etec.exerc.trabalho.controller;

import br.com.etec.exerc.trabalho.model.Client;
import br.com.etec.exerc.trabalho.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/all")
    public List<Client> list() {
        return clientRepository.findAll();
    }

}
