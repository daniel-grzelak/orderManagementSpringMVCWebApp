package com.daniel.dto;

import com.daniel.domain.enums.EGuarantee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {


    private Long id;
    @Pattern(regexp = "[A-Z\\s]+", message = "Name must contain only big letters and spaces.")
    private String name;
    @Min(value = 0, message = "Price must be minimum 0.")
    private BigDecimal price;
    private CategoryDto categoryDto;
    private ProducerDto producerDto;
    private List<EGuarantee> guarantee_components;

}
