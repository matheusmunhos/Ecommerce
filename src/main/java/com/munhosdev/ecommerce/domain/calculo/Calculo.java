package com.munhosdev.ecommerce.domain.calculo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Calculo {

    private BigDecimal valor;
    private int porcentagem;
}
