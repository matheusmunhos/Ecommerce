package com.munhosdev.ecommerce.service.venda;

import com.munhosdev.ecommerce.domain.calculo.Calculo;
import com.munhosdev.ecommerce.domain.calculo.CalculoDesconto;
import com.munhosdev.ecommerce.domain.client.Client;
import com.munhosdev.ecommerce.domain.product.Product;
import com.munhosdev.ecommerce.domain.venda.Venda;
import com.munhosdev.ecommerce.domain.venda.VendaDTO;
import com.munhosdev.ecommerce.domain.venda.VendaResponse;
import com.munhosdev.ecommerce.domain.venda.VendasMensaisResponse;
import com.munhosdev.ecommerce.exceptions.ProductNotFoundException;
import com.munhosdev.ecommerce.exceptions.UserNotFoundException;
import com.munhosdev.ecommerce.repository.ClientRepository;
import com.munhosdev.ecommerce.repository.ProductRepository;
import com.munhosdev.ecommerce.repository.VendaRepository;
import com.munhosdev.ecommerce.service.calculo.CalculoService;
import com.munhosdev.ecommerce.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductService productService;


    public Venda vender(VendaDTO dto) {
        Venda newVenda = new Venda(dto);
        Client newCliente = null;

        try {
            newCliente = clientRepository.findByCpf(dto.cpf());
            newVenda.setClient(newCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (newCliente == null) {
            try {
                int firstSpaceIndex = dto.nameAndLastname().indexOf(' ');
                if (firstSpaceIndex > 0) { // Verifica se há um espaço no nome e sobrenome
                    String name = dto.nameAndLastname().substring(0, firstSpaceIndex);
                    String lastname = dto.nameAndLastname().substring(firstSpaceIndex + 1);
                    System.out.println("Nome: " + name + " Sobrenome: " + lastname);

                    newCliente = clientRepository.findByFirstNameAndLastName(name, lastname);
                    newVenda.setClient(newCliente);

                    if (newCliente == null) {
                        throw new UserNotFoundException();
                    }
                } else {
                    System.out.println("Nome e sobrenome não estão no formato esperado.");
                }
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }


        if (newCliente == null) {
            newVenda.setClient(null);
        }


        Product newProduct = productRepository.findByCode(dto.productCode());
        if (newProduct == null) {
            throw new ProductNotFoundException();
        }


        BigDecimal valorComDesconto = productService.calcularValorComDesconto(dto.precoVendido(), dto.porcentagem());
        newVenda.setPrecoVendido(valorComDesconto);
        newVenda.setProduct(newProduct);
        newVenda.setPrecoOriginal(newProduct.getOriginalValue());
        newVenda.setPrecoParaVenda(newProduct.getValue());


        if (dto.quantidade() <= newProduct.getQuantity()) {
            int quantityAfter = newProduct.getQuantity() - dto.quantidade();
            newProduct.setQuantity(quantityAfter);
        } else {
            throw new RuntimeException("Produto fora de estoque");
        }


        productRepository.save(newProduct);

        return vendaRepository.save(newVenda);
    }



    public List<Venda> getAll(){
        return vendaRepository.findAll();
    }

    public void delete(){
        vendaRepository.deleteAll();
    }

//    public List<VendaResponse> getVendasByMonthAndYear(int month, int year) {
//        LocalDateTime startDateTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
//        LocalDateTime endDateTime = LocalDateTime.of(year, month, YearMonth.of(year, month).lengthOfMonth(), 23, 59, 59, 999);
//
//        List<Venda> vendas = vendaRepository.findByDataVendaBetween(startDateTime, endDateTime);
//
//        return vendas.stream()
//                .map(VendaResponse::new)
//                .peek(vendaResponse -> vendaResponse.setLucroTotal(vendaResponse.getPrecoVendido().multiply(BigDecimal.valueOf(vendaResponse.getQuantidade()))))
//                .collect(Collectors.toList());
//    }

    public VendasMensaisResponse getVendasByMonthAndYear(int month, int year) {
        LocalDateTime startDateTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(year, month, YearMonth.of(year, month).lengthOfMonth(), 23, 59, 59, 999);

        List<Venda> vendas = vendaRepository.findByDataVendaBetween(startDateTime, endDateTime);

        List<VendaResponse> vendasResponse = vendas.stream()
                .map(venda -> new VendaResponse(venda, venda.getProduct().getOriginalValue()))
                .collect(Collectors.toList());

        BigDecimal lucroTotalMensal = vendasResponse.stream()
                .map(VendaResponse::calcularLucroTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new VendasMensaisResponse(vendasResponse, lucroTotalMensal);
    }




}
