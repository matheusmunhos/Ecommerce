package com.munhosdev.ecommerce.domain.calculo;

import java.math.BigDecimal;

public record CalculoDTO(
        BigDecimal valorDeVenda,
        BigDecimal valorDeCusto,
        BigDecimal lucro
) {
}
