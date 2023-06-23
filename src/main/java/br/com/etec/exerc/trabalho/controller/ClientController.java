package br.com.etec.exerc.trabalho.controller;

import br.com.etec.exerc.trabalho.model.Client;
import br.com.etec.exerc.trabalho.repository.ClientRepository;
import br.com.etec.exerc.trabalho.repository.filter.ClientFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

  @Autowired
  private ClientRepository clientRepository;

  @GetMapping("/all")
  public List<Client> list() {
    return clientRepository.findAll();
  }

  @GetMapping
  public Page<Client> search(ClientFilter clientFilter, Pageable pageable) {
    return clientRepository.filter(clientFilter, pageable);
  }

}
