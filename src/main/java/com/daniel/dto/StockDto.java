package com.daniel.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDto {


    private Long id;
    @Min(value = 0, message = "Quantity of an item must be minimum 0.")
    private Integer quantity;
    private ProductDto productDto;
    private ShopDto shopDto;
}
