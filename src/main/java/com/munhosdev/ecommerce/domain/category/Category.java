package com.munhosdev.ecommerce.domain.category;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "categories")
public class Category {

    @Id
    private String id;

    private String name;
    private String description;
    private String code;



    public Category (CategoryDTO categoryDTO){
        this.name = categoryDTO.name();
        this.description = categoryDTO.description();
        this.code = categoryDTO.code();
    }

}
