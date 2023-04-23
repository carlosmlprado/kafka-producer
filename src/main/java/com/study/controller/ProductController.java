package com.study.controller;

import com.study.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        ProductDTO p1 = new ProductDTO(UUID.randomUUID(), "product1", new BigDecimal(10.0));
        ProductDTO p2 = new ProductDTO(UUID.randomUUID(), "product2", new BigDecimal(11.0));
        ProductDTO p3 = new ProductDTO(UUID.randomUUID(), "product3", new BigDecimal(12.0));
        ProductDTO p4 = new ProductDTO(UUID.randomUUID(), "product1", new BigDecimal(13.0));
        ProductDTO p5 = new ProductDTO(UUID.randomUUID(), "product5", new BigDecimal(14.0));

        List<ProductDTO> products = Arrays.asList(p1, p2, p3, p4, p5);
        Predicate<ProductDTO> predicate = p -> p.getName().equals("product1");

        System.out.println(products.stream().filter(predicate).collect(Collectors.toList()));

        return null;
    }
}
