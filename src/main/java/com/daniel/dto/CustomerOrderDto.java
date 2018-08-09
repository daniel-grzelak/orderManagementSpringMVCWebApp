package com.daniel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDto {


    private Long id;
    private LocalDateTime date;
    @Min(value = 0, message = "Discount must be minimum 0.")
    @Max(value = 1, message = "Discount can be maximum 1.")
    private BigDecimal discount;
    private Integer quantity;
    private CustomerDto customerDto;
    private PaymentDto paymentDto;
    private ProductDto productDto;
    private StockDto stockDto;
}
