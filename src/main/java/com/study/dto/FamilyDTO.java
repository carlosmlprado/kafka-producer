package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDTO {

    private Long id;
    private Double income;
    private Integer dependents;
    private Integer points;
    private String email;

}
