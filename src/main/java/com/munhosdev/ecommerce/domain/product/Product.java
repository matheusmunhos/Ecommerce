package com.munhosdev.ecommerce.domain.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    private String name;
    private String code;
    private BigDecimal value;
    private String description;


    public Product (ProductDTO dto){
        this.name = dto.name();
        this.code = dto.code();
        this.value = dto.value();
        this.description = dto.description();
    }

}
