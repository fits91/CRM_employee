/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.model;

import com.fits.beans.Employee;
import java.util.List;
import java.util.Map;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Model of table
 *
 * @author fits-dev
 */
public class EmployeeTableModel implements TableModel {

    private List<Employee> employeesList;
    private Map<Integer, String> employeeMap;

    public EmployeeTableModel(List<Employee> employeesList, Map<Integer, String> employeeMap) {
        this.employeesList = employeesList;
        this.employeeMap = employeeMap;
    }

    @Override
    public int getRowCount() {
        return employeesList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Код";
            case 1:
                return "ФИО";
            case 2:
                return "Руководитель";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employeesList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getFio();
            case 2:
                return employeeMap.get(employee.getChef());
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }

}
