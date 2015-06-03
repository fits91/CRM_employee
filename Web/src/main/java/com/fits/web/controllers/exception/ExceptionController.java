/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.web.controllers.exception;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fits-dev
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleCustomException(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("error", "Ошибка выборки с базы данных");
        return model;
    }

    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    public ModelAndView cannotGetJdbcConnectionException(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("error", "Ошибка соединения с базой данных");
        return model;
    }

    @ExceptionHandler(SQLServerException.class)
    public ModelAndView sqlServerException(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("error", "Ошибка соединения с базой данных");
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("error", "Ошибка. Проверте настройки базы данных");
        return model;
    }

}
