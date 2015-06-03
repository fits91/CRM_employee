/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.swingclient;

import com.fits.beans.Employee;
import com.fits.model.AddNewPanel;
import com.fits.rest.client.FactoryBuilder;
import com.fits.service.EmployeeService;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author fits-dev
 */
public class AddEmployeeDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;

    private JLabel fioLabel;
    private JLabel chefLabel;

    private JTextField fioField;
    private JComboBox comboBox;

    private EmployeeService es;
    private List<Employee> chiefList;
    private List<Employee> employeeList;

    public AddEmployeeDialog() {
        es = FactoryBuilder.getEs();
        employeeList = es.getAllEmployees();
        fioLabel = new JLabel("ФИО");
        chefLabel = new JLabel("Руководитель");
        fioField = new JTextField();
        okButton = new JButton("ОК");
        cancelButton = new JButton("Отмена");
        setChiefList();
        setLayout(new GridLayout(3, 2));
        setSize(420, 100);
        setLocationRelativeTo(null);
        setTitle("Добавление сотрудника");
        setComboBox();
        addComponents();
        setVisible(true);
        buttonListener();
    }

    private void setChiefList() {

        chiefList = new ArrayList<>();
        for (Employee emp : employeeList) {
            if (emp.getChef() == 0) {
                chiefList.add(emp);
            }
        }

    }

    private void addComponents() {

        add(fioLabel);
        add(fioField);
        add(chefLabel);
        add(comboBox);
        add(okButton);
        add(cancelButton);

    }

    private void setComboBox() {
        String[] items = new String[chiefList.size() + 1];
        int count = 0;
        for (Employee emp : chiefList) {
            items[count] = emp.getFio();
            count++;
        }
        comboBox = new JComboBox(items);
        System.out.println(Arrays.toString(items));
    }

    private void buttonListener() {
        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (fioField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(rootPane, "Введите все параметры");
                            return;
                        }

                        addEmployee();
                    }
                });

                cancelButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                return null;
            }

        }.execute();
    }

    private void addEmployee() {
        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                Employee newEmployee = new Employee();
                newEmployee.setFio(fioField.getText());
                for (Employee empl : employeeList) {
                    if (empl.getFio().equals(comboBox.getSelectedItem())) {
                        newEmployee.setChef(empl.getId());
                    } else {
                        newEmployee.setChef(0);
                    }
                }
                dispose();
                es.addEmployee(newEmployee);
                AddNewPanel.repaintTree(new EmployeeTablePanel().getMain());
                return null;
            }

        }.execute();

    }

}
