package br.com.etec.exerc.trabalho.repository.projections;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PayAccountDTO {

  private Integer id;

  private LocalDate date;

  private LocalDate date_exp;

  private String name; // nome Client

  private Double value;

}
