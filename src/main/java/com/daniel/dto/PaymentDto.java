package com.daniel.dto;


import com.daniel.domain.enums.EPayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {


    private Long id;
    private EPayment payment;

}
