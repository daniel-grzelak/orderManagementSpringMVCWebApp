package com.daniel.controllers;

import com.daniel.domain.enums.EPayment;
import com.daniel.dto.CustomerDto;
import com.daniel.dto.CustomerOrderDto;
import com.daniel.dto.ProductDto;
import com.daniel.dto.StockDto;
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

@Controller
public class CustomerOrderController {

    private MyService myService;


    public CustomerOrderController(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping(value = "/customerOrder", method = RequestMethod.GET)
    public String formGet(Model m) {
        CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        m.addAttribute("customerOrder", customerOrderDto);
        List<CustomerDto> customerList = myService.getAllCustomers();
        m.addAttribute("customerList", customerList);
        List<ProductDto> productList = myService.getAllProducts();
        m.addAttribute("productList", productList);
        m.addAttribute("payments", EPayment.values());
        List<CustomerOrderDto> customerOrderDtos = myService.getAllCustomerOrders();
        m.addAttribute("customerOrderDtos", customerOrderDtos);
        List<StockDto> stockDtos = myService.getAllStocks();
        m.addAttribute("stockDtos", stockDtos);
        return "customerOrder";
    }

    @RequestMapping(value = "/customerOrder", method = RequestMethod.POST)
    public String formPost(@Valid @ModelAttribute CustomerOrderDto customerOrder, BindingResult result, Model m, HttpServletRequest request) {
        if (result.hasErrors())
        {

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
                throw new CustomException(error.getObjectName(), error.getDefaultMessage());
            }

            return "customerOrder";
        }

        myService.addCustomerOrder(customerOrder);

        CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        m.addAttribute("customerOrder", customerOrderDto);
        List<CustomerDto> customerList = myService.getAllCustomers();
        m.addAttribute("customerList", customerList);
        List<ProductDto> productList = myService.getAllProducts();
        m.addAttribute("productList", productList);
        m.addAttribute("payments", EPayment.values());
        List<CustomerOrderDto> customerOrderDtos = myService.getAllCustomerOrders();
        m.addAttribute("customerOrderDtos", customerOrderDtos);
        List<StockDto> stockDtos = myService.getAllStocks();
        m.addAttribute("stockDtos", stockDtos);
        return "customerOrder";
    }
}
