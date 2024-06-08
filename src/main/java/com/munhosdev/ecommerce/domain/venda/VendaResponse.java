package com.munhosdev.ecommerce.domain.venda;

import com.munhosdev.ecommerce.domain.client.Client;
import com.munhosdev.ecommerce.domain.product.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VendaResponse {

    private String id;
    private LocalDateTime dataVenda;
    private int quantidade;
    private BigDecimal precoVendido;
    private BigDecimal precoParaVenda;
    private BigDecimal precoOriginal;
    private String formaPagamento;
    private String observacoes;
    private int porcentagem;
    private Client client;
    private Product product;

    private BigDecimal lucroTotal;


    public VendaResponse(Venda venda, BigDecimal originalValue) {
        this.id = venda.getId();
        this.dataVenda = venda.getDataVenda();
        this.quantidade = venda.getQuantidade();
        this.precoVendido = venda.getPrecoVendido();
        this.precoParaVenda = venda.getPrecoParaVenda();
        this.precoOriginal = venda.getPrecoOriginal();
        this.formaPagamento = venda.getFormaPagamento();
        this.observacoes = venda.getObservacoes();
        this.porcentagem = venda.getPorcentagem();
        this.client = venda.getClient();
        this.product = venda.getProduct();
        this.lucroTotal = venda.getPrecoVendido().multiply(BigDecimal.valueOf(venda.getQuantidade())).subtract(originalValue.multiply(BigDecimal.valueOf(venda.getQuantidade())));
    }

    public BigDecimal calcularLucroTotal() {
        BigDecimal valorCustoTotal = product.getOriginalValue().multiply(BigDecimal.valueOf(quantidade));
        BigDecimal valorVendaTotal = precoVendido.multiply(BigDecimal.valueOf(quantidade));
        BigDecimal lucroPorUnidade = precoVendido.subtract(product.getOriginalValue());
        BigDecimal lucroTotal = lucroPorUnidade.multiply(BigDecimal.valueOf(quantidade));
        return lucroTotal;
    }
}
