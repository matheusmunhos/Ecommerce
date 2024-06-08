package com.munhosdev.ecommerce.controller.calculo;

import com.munhosdev.ecommerce.domain.calculo.Calculo;
import com.munhosdev.ecommerce.domain.calculo.CalculoDTO;
import com.munhosdev.ecommerce.domain.calculo.CalculoDesconto;
import com.munhosdev.ecommerce.service.calculo.CalculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/calculo")
public class CalculoController {

    @Autowired
    private CalculoService service;


    @PostMapping
    public ResponseEntity<CalculoDTO> calcular(@RequestBody Calculo calculo){
        return ResponseEntity.ok(service.calcular(calculo));
    }

    @PostMapping("/desconto")
    public ResponseEntity<CalculoDesconto> desconto(@RequestBody Calculo calculo){
        return ResponseEntity.ok(service.calculoDesconto(calculo));
    }
}
