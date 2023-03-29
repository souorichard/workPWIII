package br.com.etec.exerc.trabalho.controller;

import br.com.etec.exerc.trabalho.model.PayAccount;
import br.com.etec.exerc.trabalho.repository.PayAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payaccounts")
public class PayAccountController {

    @Autowired
    private PayAccountRepository payAccountRepository;

    @GetMapping("/all")
    public List<PayAccount> list() {
        return payAccountRepository.findAll();
    }

}
