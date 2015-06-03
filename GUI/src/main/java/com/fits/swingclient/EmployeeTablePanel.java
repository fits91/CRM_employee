/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.swingclient;

import com.fits.beans.Employee;
import com.fits.model.EmployeeTableModel;
import com.fits.rest.client.FactoryBuilder;
import com.fits.service.EmployeeService;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

/**
 *
 * @author fits-dev
 */
public class EmployeeTablePanel {

    public JMenuBar menu;
    JPanel main;
    EmployeeService es;
    List<Employee> employeesList;
    Map<Integer, String> employeeMap;
    JTable employeesTable;
    JScrollPane scrlPane;

    public EmployeeTablePanel() {
        es = FactoryBuilder.getEs();
        employeesList = es.getAllEmployees();
        employeeMap = es.mappingFioID();
        main = new JPanel(new BorderLayout());
        employeesTable = createTable(employeesList);
        scrlPane = new JScrollPane(employeesTable);
        main.add(scrlPane, BorderLayout.CENTER);

    }

    JTable createTable(List<Employee> listEmployee) {
        TableModel model = new EmployeeTableModel(listEmployee, employeeMap);
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    public JPanel getMain() {
        return main;
    }

}
