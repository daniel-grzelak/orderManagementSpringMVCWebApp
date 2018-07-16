package com.daniel.controllers;

import com.daniel.dao.interfaces.CountryDao;
import com.daniel.dao.interfaces.ShopDao;
import com.daniel.domain.enums.EGuarantee;
import com.daniel.dto.CategoryDto;
import com.daniel.dto.ProducerDto;
import com.daniel.dto.ProductDto;
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
import java.util.EnumSet;
import java.util.List;

@Controller
public class ProductController {

    private MyService myService;


    public ProductController(MyService myService, ShopDao shopDao, CountryDao countryDao) {
        this.myService = myService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String formGet(Model m) {
        ProductDto productDto = new ProductDto();
        m.addAttribute("product", productDto);
        List<CategoryDto> categoryList = myService.getAllCategories();
        m.addAttribute("categoryList", categoryList);
        List<ProducerDto> producerList = myService.getAllProducers();
        m.addAttribute("producerList", producerList);
        m.addAttribute("guarantees", EGuarantee.values());

        return "product";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String formPost(@Valid @ModelAttribute ProductDto product, BindingResult result, Model m, HttpServletRequest request) {
        if (result.hasErrors())
        {

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
                throw new CustomException(error.getObjectName(), error.getDefaultMessage());
            }

            return "product";
        }

        myService.addProduct(product);

        ProductDto productDto = new ProductDto();
        m.addAttribute("product", productDto);
        List<CategoryDto> categoryList = myService.getAllCategories();
        m.addAttribute("categoryList", categoryList);
        List<ProducerDto> producerList = myService.getAllProducers();
        m.addAttribute("producerList", producerList);
        EnumSet<EGuarantee> guaranteeList = EnumSet.allOf(EGuarantee.class);
        m.addAttribute("guaranteeList", guaranteeList);

        return "product";
    }
}
