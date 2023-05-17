package br.com.etec.exerc.trabalho.repository.client;

import br.com.etec.exerc.trabalho.model.Client;
import br.com.etec.exerc.trabalho.repository.filter.ClientFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientRepositoryQuery {

    public Page<Client> filter(ClientFilter clientFilter, Pageable pageable);

}
