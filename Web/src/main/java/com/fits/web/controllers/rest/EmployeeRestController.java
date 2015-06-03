/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.web.controllers.rest;

import com.fits.beans.Employee;
import com.fits.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller
 *
 * @author fits-dev
 */
@RestController
@RequestMapping("/admin/emloyees")
public class EmployeeRestController {

    @Autowired
    @Qualifier("employeeBusiness")
    EmployeeService es;

    /**
     * Add Object to resource
     *
     * @param employee added Object to resource
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody Employee employee) {
        es.addEmployee(employee);
    }

    /**
     * Return all Objects from resource
     *
     * @return all Objects from resource
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Employee> getAllEmployees() {
        return es.getAllEmployees();
    }

    /**
     *
     * @param id - id of returned resource
     * @return the Object from resource
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Employee getEmployee(@PathVariable int id) {
        return es.getEmployee(id);
    }

    /**
     * Delete Object from resource
     *
     * @param id - id of deleted Object
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delEmployee(@PathVariable int id) {
        es.delEmployee(id);
    }

    /**
     *
     * @param employee - created Object
     * @param id - id of created object
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editEmployee(@RequestBody Employee employee, @PathVariable int id) {
        es.updateEmployee(employee);
    }

}
