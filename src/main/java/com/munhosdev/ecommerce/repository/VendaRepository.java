package com.munhosdev.ecommerce.repository;

import com.munhosdev.ecommerce.domain.venda.Venda;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends MongoRepository<Venda, String> {

    List<Venda> findByDataVendaBetween(LocalDateTime startDate, LocalDateTime endDate);
}
