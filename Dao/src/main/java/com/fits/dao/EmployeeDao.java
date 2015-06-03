/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.dao;

import com.fits.beans.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fits-dev
 */
@Repository
public class EmployeeDao {

    @Autowired
    private JdbcTemplate template;

    class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFio(rs.getString("fio"));
            employee.setChef(rs.getInt("chef"));
            return employee;
        }
    }

    public void addEmployee(Employee employee) {
        System.out.println(template);
        template.update("EXEC addUser ?, ?", employee.getFio(), employee.getChef());
    }

    public void delEmployee(int emloyeeID) {
        template.update("DELETE FROM Employee "
                + "WHERE id = ?", emloyeeID);
    }

    public void updateEmployee(Employee employee) {
        String chef = Integer.toString(employee.getChef());
        if (employee.getChef() == 0) {
            chef = null;
        }
        template.update("UPDATE Employee "
                + "SET fio = ?, chef = ? "
                + "WHERE id = ?", employee.getFio(), chef, employee.getId());
    }

    public Employee getEmployee(int employeeID) {
        return template.queryForObject("SELECT * FROM Employee "
                + "WHERE id = ?", new EmployeeMapper(), employeeID);
    }

    public List<Employee> getAllEmployees() {
        return template.query("SELECT * FROM Employee", new EmployeeMapper());
    }

    public Map<Integer, String> mappingFioID() {
        List<Employee> list = getAllEmployees();
        Map map = new HashMap();
        for (Employee employee : list) {
            map.put(employee.getId(), employee.getFio());
        }
        return map;
    }

}
