package com.daniel.controllers;

import com.daniel.dto.CountryDto;
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
import java.util.stream.Collectors;

@Controller
public class ShopController {

    private MyService myService;

    public ShopController(MyService myService) {
        this.myService = myService;

    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String formGet(Model m) {
        ShopDto shopDto = new ShopDto();
        m.addAttribute("shop", shopDto);
        List<CountryDto> countryList = myService.getAllCountries();
        m.addAttribute("countryList", countryList);
        List<ShopDto> shopDtos = myService.getAllShops();
        shopDtos
                .forEach(s -> s.setStockDtos(myService.getAllStocksForShopDto(s).stream().map(id -> StockDto.builder().productDto(id.getProductDto()).quantity(id.getQuantity()).build())
                        .collect(Collectors.toList())));
        m.addAttribute("shopDtos", shopDtos);
        return "shop";
    }

    @RequestMapping(value = "/shop", method = RequestMethod.POST)
    public String formPost(@Valid @ModelAttribute ShopDto shop, BindingResult result, Model m, HttpServletRequest request) {
        if (result.hasErrors())
        {

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
                throw new CustomException(error.getObjectName(), error.getDefaultMessage());
            }

            return "shop";
        }

        myService.addShop(shop);

        ShopDto shopDto = new ShopDto();
        m.addAttribute("shop", shopDto);
        List<CountryDto> countryList = myService.getAllCountries();
        m.addAttribute("countryList", countryList);
        List<ShopDto> shopDtos = myService.getAllShops();
        shopDtos
                .forEach(s -> s.setStockDtos(myService.getAllStocksForShopDto(s).stream().map(id -> StockDto.builder().productDto(id.getProductDto()).quantity(id.getQuantity()).build())
                        .collect(Collectors.toList())));
        m.addAttribute("shopDtos", shopDtos);

        return "shop";
    }
}
