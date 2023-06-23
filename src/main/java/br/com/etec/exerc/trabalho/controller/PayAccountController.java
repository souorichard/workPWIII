package br.com.etec.exerc.trabalho.controller;

import br.com.etec.exerc.trabalho.model.PayAccount;
import br.com.etec.exerc.trabalho.repository.PayAccountRepository;
import br.com.etec.exerc.trabalho.repository.filter.PayAccountFilter;
import br.com.etec.exerc.trabalho.repository.projections.PayAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payaccounts")
public class PayAccountController {

    @Autowired
    private PayAccountRepository payAccountRepository;

    @GetMapping("/all")
    public List<PayAccount> list() {
        return payAccountRepository.findAll();
    }

    @GetMapping
    public Page<PayAccountDTO> search(PayAccountFilter payAccountFilter, Pageable pageable) {
        return payAccountRepository.filter(payAccountFilter, pageable);
    }

}
