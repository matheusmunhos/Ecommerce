package com.munhosdev.ecommerce.domain.product;

import java.math.BigDecimal;

public record ProductDTO(

        String name,
        String description,
        BigDecimal value,
        String code,
        String categoryCode,
        int porcentagem,
        int quantity
) {
}
