package com.munhosdev.ecommerce.domain.venda;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VendasMensaisResponse {
    private List<VendaResponse> vendas;
    private BigDecimal lucroTotalMensal;

    public VendasMensaisResponse(List<VendaResponse> vendas, BigDecimal lucroTotalMensal) {
        this.vendas = vendas;
        this.lucroTotalMensal = lucroTotalMensal;
    }
}
