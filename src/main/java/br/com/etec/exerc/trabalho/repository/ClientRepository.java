package br.com.etec.exerc.trabalho.repository;

import br.com.etec.exerc.trabalho.model.Client;
import br.com.etec.exerc.trabalho.repository.client.ClientRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>, ClientRepositoryQuery {
}
