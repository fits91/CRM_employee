/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.swingclient;

import com.fits.beans.Employee;
import com.fits.model.AddNewPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author fits-dev
 */
public class EmployeePanel extends EmployeeTablePanel {

    private String employeeName;
    private Employee editEmployee;
    private Employee employee;
    private List<Employee> chefList;

    private JPanel employeeCard;
    private JLabel idLabel, fioLabel, chefLabel;
    private JTextField idField, fioField, chefField;
    private JButton saveButton;
    private JButton deleteButton;
    private JComboBox comboBox;

    public EmployeePanel(String employeeName) {
        super();
        this.employeeName = employeeName;
        chefList = getChief();
        setEmployee();
        getEmployeeCard();
        this.employeesTable = createTable(getChildrenList());
        this.scrlPane = new JScrollPane(employeesTable);
        main.add(employeeCard, BorderLayout.NORTH);
        main.add(scrlPane, BorderLayout.CENTER);
    }

    private void setEmployee() {
        for (Employee empl : employeesList) {
            if ((empl.getFio()).equals(employeeName)) {
                employee = empl;
            }
        }
    }

    private List<Employee> getChildrenList() {
        List<Employee> childrenList = new ArrayList<>();
        for (Employee empl : employeesList) {
            if (empl.getChef() == employee.getId()) {
                childrenList.add(empl);
            }
        }
        return childrenList;
    }

    private List<Employee> getChief() {
        List<Employee> chefList = new ArrayList<>();
        for (Employee empl : employeesList) {
            if (empl.getChef() == 0) {
                chefList.add(empl);
            }
        }
        return chefList;
    }

    private void setComboBox() {

        String[] chefName = new String[chefList.size()];
        int count = 0;
        for (Employee empl : chefList) {
            chefName[count] = empl.getFio();
            count++;
        }

        comboBox = new JComboBox(chefName);
        comboBox.setSelectedItem(employeeMap.get(employee.getChef()));
    }

    private void getEmployeeCard() {
        employeeCard = new JPanel();

        idLabel = new JLabel("Код ");
        fioLabel = new JLabel("ФИО ");
        saveButton = new JButton("Сохранить");
        deleteButton = new JButton("Удалить");

        idField = new JTextField(Integer.toString(employee.getId()));
        idField.setEnabled(false);

        fioField = new JTextField(employee.getFio());
        fioField.setEnabled(false);
        employeeCard.add(idLabel);
        employeeCard.add(idField);
        employeeCard.add(fioLabel);
        employeeCard.add(fioField);
        if (employee.getChef() != 0) {
            chefLabel = new JLabel("Руководитель ");
            employeeCard.add(chefLabel);
            fioField.setEnabled(true);
            setComboBox();
            employeeCard.add(comboBox);
            employeeCard.add(saveButton);
            employeeCard.add(deleteButton);
        }
        addButtonActionListener();

    }

    private void addButtonActionListener() {
        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                saveButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        if (fioField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(saveButton, "Заполните все поля!");
                            return;
                        }

                        int chefID = 0;
                        for (Employee emp : employeesList) {
                            if (emp.getFio().equals(comboBox.getSelectedItem())) {
                                chefID = emp.getId();
                            }
                        }
                        editEmployee = new Employee();
                        editEmployee.setId(employee.getId());
                        editEmployee.setFio(fioField.getText());
                        editEmployee.setChef(chefID);
                        editEmployee();

                    }
                });

                deleteButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteEmployee();
                    }
                });
                return null;
            }

        }.execute();

    }

    private void editEmployee() {
        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                es.updateEmployee(editEmployee);
                AddNewPanel.repaintTree(new EmployeePanel(editEmployee.getFio()).getMain());
                return null;
            }

        }.execute();
    }

    private void deleteEmployee() {
        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                es.delEmployee(employee.getId());
                AddNewPanel.repaintTree(new EmployeeTablePanel().getMain());
                return null;
            }

        }.execute();
    }

}
