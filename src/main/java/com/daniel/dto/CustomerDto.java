package com.daniel.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {


    private Long id;
    @Min(value = 18, message = "You must be minimum 18 years old.")
    private Integer age;
    @Pattern(regexp = "[A-Z\\s]+", message = "Name must contain only big letters and spaces.")
    private String name;
    @Pattern(regexp = "[A-Z\\s]+", message = "Surname must contain only big letters and spaces.")
    private String surname;
    private CountryDto countryDto;
    private List<CustomerOrderDto> customerOrderDtos;


}
