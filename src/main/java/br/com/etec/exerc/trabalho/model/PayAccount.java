package br.com.etec.exerc.trabalho.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "payaccount")
public class PayAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date date;

    @Column
    private Date date_exp;

    @ManyToOne
    @JoinColumn(name = "idclient")
    private Client client;

    @Column
    private BigDecimal value;

}
