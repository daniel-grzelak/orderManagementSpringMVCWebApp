package com.daniel.controllers;

import com.daniel.dto.CountryDto;
import com.daniel.dto.ProducerDto;
import com.daniel.dto.TradeDto;
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
public class ProducerController {

    private MyService myService;


    public ProducerController(MyService myService) {
        this.myService = myService;

    }

    @RequestMapping(value = "/producer", method = RequestMethod.GET)
    public String formGet(Model m) {
        ProducerDto producerDto = new ProducerDto();
        m.addAttribute("producer", producerDto);
        List<CountryDto> countryList = myService.getAllCountries();
        m.addAttribute("countryList", countryList);
        List<TradeDto> tradeList = myService.getAllTrades();
        m.addAttribute("tradeList", tradeList);
        List<ProducerDto> producerDtos = myService.getAllProducers();
        producerDtos.forEach(p -> p.setProductDtos(myService.getAllProducts().stream().filter(pr -> pr.getProducerDto().getId().equals(p.getId())).collect(Collectors.toList())));
        m.addAttribute("producerDtos", producerDtos);
        return "producer";
    }

    @RequestMapping(value = "/producer", method = RequestMethod.POST)
    public String formPost(@Valid @ModelAttribute ProducerDto producer, BindingResult result, Model m, HttpServletRequest request) {
        if (result.hasErrors())
        {

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
                throw new CustomException(error.getObjectName(), error.getDefaultMessage());
            }

            return "producer";
        }

        myService.addProducer(producer);

        ProducerDto producerDto = new ProducerDto();
        m.addAttribute("producer", producerDto);
        List<CountryDto> countryList = myService.getAllCountries();
        m.addAttribute("countryList", countryList);
        List<TradeDto> tradeList = myService.getAllTrades();
        m.addAttribute("tradeList", tradeList);
        List<ProducerDto> producerDtos = myService.getAllProducers();
        producerDtos.forEach(p -> p.setProductDtos(myService.getAllProducts().stream().filter(pr -> pr.getProducerDto().getId().equals(p.getId())).collect(Collectors.toList())));
        m.addAttribute("producerDtos", producerDtos);
        return "producer";
    }
}

