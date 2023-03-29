package br.com.etec.exerc.trabalho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<PayAccount> clientaccounts = new ArrayList<>();
}
