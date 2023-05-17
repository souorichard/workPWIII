package br.com.etec.exerc.trabalho.repository.payaccount;

import br.com.etec.exerc.trabalho.model.PayAccount;
import br.com.etec.exerc.trabalho.repository.filter.PayAccountFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PayAccountRepositoryQuery {

    public Page<PayAccount> filter(PayAccountFilter payAccountFilter, Pageable pageable);

}
