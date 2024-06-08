package com.munhosdev.ecommerce.domain.venda;

import com.munhosdev.ecommerce.domain.client.Client;
import com.munhosdev.ecommerce.domain.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "vendas")
public class Venda {

    @Id
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

    public Venda(VendaDTO dto){
        this.formaPagamento = dto.formaPagamento();
        this.observacoes = dto.observacoes();
        this.porcentagem = dto.porcentagem();
        this.quantidade = dto.quantidade();
        this.dataVenda = LocalDateTime.now();
    }


}
