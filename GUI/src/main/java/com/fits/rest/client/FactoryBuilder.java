/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.rest.client;

import com.fits.service.EmployeeService;

/**
 *
 * @author fits-dev
 */
public class FactoryBuilder {

    private static EmployeeService es;

    public static EmployeeService getEs() {
        return es;
    }

    public static void setEs(EmployeeService es) {
        FactoryBuilder.es = es;
    }

}
