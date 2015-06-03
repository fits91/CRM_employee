/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.rest.client;

import com.fits.beans.Employee;
import com.fits.service.EmployeeService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

/**
 * Rest service
 *
 * @author fits-dev
 */
public class EmployeeRestClient implements EmployeeService {

    private String siteURL;
    private RestTemplate restTemplate;

    public EmployeeRestClient(String siteURL) {
        this.siteURL = siteURL;
        restTemplate = new RestTemplate();
    }

    @Override
    public synchronized void addEmployee(Employee employee) {
        String url = siteURL + "/admin/emloyees";
        restTemplate.postForObject(url, employee, Employee.class);
    }

    @Override
    public synchronized void delEmployee(int employeeID) {
        String url = siteURL + "/admin/emloyees/{id}";
        restTemplate.delete(url, employeeID);
    }

    @Override
    public synchronized void updateEmployee(Employee employee) {
        String url = siteURL + "/admin/emloyees/{id}";
        restTemplate.put(url, employee, employee.getId());
    }

    @Override
    public synchronized Employee getEmployee(int employeeID) {
        String url = siteURL + "/admin/emloyees/{id}";
        return restTemplate.getForObject(url, Employee.class, employeeID);
    }

    @Override
    public synchronized List<Employee> getAllEmployees() {
        String url = siteURL + "/admin/emloyees";
        return Arrays.asList(restTemplate.getForObject(url, Employee[].class));
    }

    @Override
    public synchronized Map<Integer, String> mappingFioID() {
        String url = siteURL + "/admin/emloyees";
        Map<Integer, String> map = new HashMap<>();
        List<Employee> list = Arrays.asList(restTemplate.getForObject(url, Employee[].class));
        for (Employee employee : list) {
            map.put(employee.getId(), employee.getFio());
        }
        return map;
    }

}
