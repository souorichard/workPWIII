package br.com.etec.exerc.trabalho.repository.filter;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class PayAccountFilter {

  private LocalDate date;

  private LocalDate date_exp;

  private String name; // nome Cliente

}
