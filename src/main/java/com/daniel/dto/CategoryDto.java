package com.daniel.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {


    private Long id;
    @Pattern(regexp = "[A-Z\\s]+", message = "Name must contain only big letters and spaces.")
    private String name;


}
