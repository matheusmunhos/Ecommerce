package com.munhosdev.ecommerce.domain.client;

import com.munhosdev.ecommerce.domain.address.AddressDTO;

public record ClientDTO(String firstName, String lastName, String cpf, String number, AddressDTO address) {
}
