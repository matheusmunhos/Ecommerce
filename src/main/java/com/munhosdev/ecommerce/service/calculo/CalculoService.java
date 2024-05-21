package com.munhosdev.ecommerce.service.calculo;

import com.munhosdev.ecommerce.domain.calculo.Calculo;
import com.munhosdev.ecommerce.domain.calculo.CalculoDTO;
import com.munhosdev.ecommerce.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CalculoService {

    @Autowired
    private final ProductService productService;

    public CalculoDTO calcular(Calculo calculo) {

        BigDecimal valorFinal = productService.calcularValorComPorcentagem(calculo.getValor(), calculo.getPorcentagem());
        BigDecimal valorInicial = calculo.getValor();
        BigDecimal lucro = valorFinal.subtract(valorInicial);
        CalculoDTO calculoDTO = new CalculoDTO(valorFinal, valorInicial, lucro);
        return calculoDTO;
    }
}
