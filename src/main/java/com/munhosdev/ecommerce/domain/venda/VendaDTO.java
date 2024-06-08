package com.munhosdev.ecommerce.domain.venda;

import java.math.BigDecimal;

public record VendaDTO(

        String productCode,
        String nameAndLastname,
        String cpf,
        int quantidade,
        BigDecimal precoVendido,
        String formaPagamento,
        String observacoes,
        int porcentagem
) {
}
