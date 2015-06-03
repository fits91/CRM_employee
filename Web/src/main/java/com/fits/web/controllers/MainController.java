/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.web.controllers;

import com.fits.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author fits-dev
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    @Qualifier("employeeBusiness")
    EmployeeService es;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public String index(ModelMap model) {
        model.addAttribute("info", "Все хорошо, сервер запущен");
        es.getAllEmployees();
        return "index";
    }
}
