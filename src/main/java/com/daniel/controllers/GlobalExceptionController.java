package com.daniel.controllers;


import com.daniel.dao.interfaces.GenericDao;
import com.daniel.domain.Errors;
import com.daniel.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionController {

    @Autowired
    GenericDao<Errors> errorsGenericDao;

    @ExceptionHandler(value = {CustomException.class})
    public String exceptionHandlerMethod(Model model, CustomException e)
    {
        Errors errors = Errors.builder().date(LocalDateTime.now()).message(e.getTableName() + ";" + e.getMessage()).build();
        errorsGenericDao.add(errors);
        model.addAttribute("tableName", e.getTableName());
        model.addAttribute("message", e.getMessage());
        return "error";

    }
}
