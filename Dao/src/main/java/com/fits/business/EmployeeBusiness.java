/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.business;

import com.fits.beans.Employee;
import com.fits.dao.EmployeeDao;
import com.fits.service.EmployeeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author fits-dev
 */
@Component
public class EmployeeBusiness implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Override
    public void delEmployee(int employeeID) {
        employeeDao.delEmployee(employeeID);
    }

    @Override
    public Employee getEmployee(int employeeID) {
        return employeeDao.getEmployee(employeeID);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = employeeDao.getAllEmployees();
        return list;
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public Map<Integer, String> mappingFioID() {
        return employeeDao.mappingFioID();
    }
    

}
