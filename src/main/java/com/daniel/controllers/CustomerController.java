package com.daniel.controllers;

import com.daniel.dto.CountryDto;
import com.daniel.dto.CustomerDto;
import com.daniel.dto.CustomerOrderDto;
import com.daniel.exceptions.CustomException;
import com.daniel.service.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CustomerController {

    private MyService myService;



    public CustomerController(MyService myService) {
        this.myService = myService;
        }




    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String formGet(Model m) {
        CustomerDto customerDto = new CustomerDto();
        m.addAttribute("customer", customerDto);
        List<CountryDto> countryList = myService.getAllCountries();
        m.addAttribute("countryList", countryList);
        List<CustomerDto> customerDtos = myService.getAllCustomers();
        customerDtos
                .forEach(c -> c.setCustomerOrderDtos(myService.getAllCustomerOrdersForCustomerDto(c).stream().map(id -> CustomerOrderDto.builder().id(id).build())
                        .collect(Collectors.toList())));
        m.addAttribute("customerDtos", customerDtos);
        return "customer";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String formPost(@Valid @ModelAttribute CustomerDto customer, BindingResult result, Model m, HttpServletRequest request) {
        if (result.hasErrors())
        {

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
                throw new CustomException(error.getObjectName(), error.getDefaultMessage());
            }

            return "customer";
        }

        myService.addCustomer(customer);

        CustomerDto customerDto = new CustomerDto();
        m.addAttribute("customer", customerDto);
        List<CountryDto> countryList = myService.getAllCountries();
        m.addAttribute("countryList", countryList);
        List<CustomerDto> customerDtos = myService.getAllCustomers();
        customerDtos
                .forEach(c -> c.setCustomerOrderDtos(myService.getAllCustomerOrdersForCustomerDto(c).stream().map(id -> CustomerOrderDto.builder().id(id).build())
                        .collect(Collectors.toList())));
        m.addAttribute("customerDtos", customerDtos);

        return "customer";
    }
}

