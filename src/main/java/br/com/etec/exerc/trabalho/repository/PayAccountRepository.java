package br.com.etec.exerc.trabalho.repository;

import br.com.etec.exerc.trabalho.model.PayAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayAccountRepository extends JpaRepository<PayAccount, Integer> {
}
