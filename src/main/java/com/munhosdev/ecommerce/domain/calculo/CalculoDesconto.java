package com.munhosdev.ecommerce.domain.calculo;

import java.math.BigDecimal;

public record CalculoDesconto(
        BigDecimal valorOriginal,
        BigDecimal valorDesconto,
        BigDecimal perda
) {
}
