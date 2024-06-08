package com.munhosdev.ecommerce.controller.venda;

import com.munhosdev.ecommerce.domain.venda.Venda;
import com.munhosdev.ecommerce.domain.venda.VendaDTO;
import com.munhosdev.ecommerce.domain.venda.VendaResponse;
import com.munhosdev.ecommerce.domain.venda.VendasMensaisResponse;
import com.munhosdev.ecommerce.service.venda.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vendas")
public class VendaController {


    @Autowired
    private VendaService vendaService;


    @PostMapping
    public ResponseEntity<Venda> cadastrarVenda(@RequestBody VendaDTO venda){
        return ResponseEntity.ok().body(vendaService.vender(venda));
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas(){
        return ResponseEntity.ok().body(vendaService.getAll());
    }

    @DeleteMapping
    public void excluirAll(){
        vendaService.delete();
    }

    @GetMapping("/mes/{mes}/{ano}")
    public ResponseEntity<VendasMensaisResponse> getByMonth(@PathVariable int mes, @PathVariable int ano){
        return ResponseEntity.ok().body(vendaService.getVendasByMonthAndYear(mes, ano));
    }

}
