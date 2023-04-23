package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Data
public class ProductDTO implements Serializable {

    private UUID id;
    private String name;
    private BigDecimal price;
}
