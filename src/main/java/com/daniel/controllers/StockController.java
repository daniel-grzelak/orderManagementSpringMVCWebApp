package com.daniel.controllers;

import com.daniel.dto.ProductDto;
import com.daniel.dto.ShopDto;
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
public class StockController {

    private MyService myService;


    public StockController(MyService myService) {
        this.myService = myService;

    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public String formGet(Model m) {
        StockDto stockDto = new StockDto();
        m.addAttribute("stock", stockDto);
        List<ProductDto> productList = myService.getAllProducts();
        m.addAttribute("productList", productList);
        List<ShopDto> shopList = myService.getAllShops();
        m.addAttribute("shopList", shopList);
        return "stock";
    }

    @RequestMapping(value = "/stock", method = RequestMethod.POST)
    public String formPost(@Valid @ModelAttribute StockDto stock, BindingResult result, Model m, HttpServletRequest request) {
        if (result.hasErrors())
        {

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
                throw new CustomException(error.getObjectName(), error.getDefaultMessage());
            }

            return "stock";
        }



        StockDto stockDto = new StockDto();
        m.addAttribute("stock", stockDto);
        List<ProductDto> productList = myService.getAllProducts();
        m.addAttribute("productList", productList);
        List<ShopDto> shopList = myService.getAllShops();
        m.addAttribute("shopList", shopList);

        return "stock";
    }
}
