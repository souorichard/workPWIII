package br.com.etec.exerc.trabalho.repository.filter;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayAccountFilter {

    private Date date;

    private Date date_exp;

    private BigDecimal value;

}
