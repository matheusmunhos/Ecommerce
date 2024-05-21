package com.munhosdev.ecommerce.domain.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Address(AddressDTO addressDTO) {
        this.logradouro = addressDTO.logradouro();
        this.bairro = addressDTO.bairro();
        this.cep = addressDTO.cep();
        this.numero = addressDTO.numero();
        this.complemento = addressDTO.complemento();
        this.cidade = addressDTO.cidade();
        this.uf = addressDTO.uf();
    }

}
