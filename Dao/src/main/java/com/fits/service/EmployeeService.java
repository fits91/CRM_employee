/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.service;

import com.fits.beans.Employee;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author fits-dev
 */
@Component
public interface EmployeeService {

    /**
     * add Object to resource
     * @param employee added object 
     */
    void addEmployee(Employee employee);

    /**
     * delete Object from resource
     * @param employeeID id of deleted Object 
     */
    void delEmployee(int employeeID);

    /**
     * upodate Object in resource
     * @param employee updated Object
     */
    void updateEmployee(Employee employee);

    /**
     * 
     * @param employeeID id of getted Object
     * @return Object from resource
     */
    Employee getEmployee(int employeeID);

    /**
     * 
     * @return all Objects from resource
     */
    List<Employee> getAllEmployees();
    
    /**
     * 
     * @return mapping id-fio   
     */
    Map<Integer, String> mappingFioID();
}
