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
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author fits-dev
 */
public class EmployeeTreePanel extends JPanel {

    private JTree tree;
    private String employeeFio;
    private EmployeeService es = FactoryBuilder.getEs();
    private List<Employee> employeeList;
    private List<Employee> employeeChildren;
    private Map<Integer, List<Employee>> mapChiefEmployee;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Сотрудники");
    private JButton addEmployee = new JButton("Добавить сотрудника");

    public EmployeeTreePanel() {
        employeeList = es.getAllEmployees();
        employeeChildren = new LinkedList<>();
        mapChiefEmployee = new HashMap<>();
        setLayout(new BorderLayout());
        addBranches();
        tree = new JTree(root);
        tree.setRootVisible(false);
        add(addEmployee, BorderLayout.NORTH);
        add(new JScrollPane(tree), BorderLayout.CENTER);
        treeListener();
        buttonListener();
        setSize(600, 600);
        setVisible(true);
    }

    public void addBranches() {
        for (Employee employee : employeeList) {
            if (employee.getChef() == 0) {
                mapChiefEmployee.put(employee.getId(), getChildren(employee));
                addLimb(new DefaultMutableTreeNode(employee.getFio()), employee.getId(), employeeList);
            }
        }

    }

    private void treeListener() {
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {

                AddNewPanel.changePanel(new EmployeePanel(e.getPath().getLastPathComponent().toString()).getMain());

            }
        });

    }

    private void buttonListener() {

        addEmployee.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeeDialog();
            }
        });

    }

    private void addLimb(DefaultMutableTreeNode treeNode, int employeeID, List<Employee> list) {
        for (Employee childEmployee : mapChiefEmployee.get(employeeID)) {
            treeNode.add(new DefaultMutableTreeNode(childEmployee.getFio()));
        }
        root.add(treeNode);
    }

    private List<Employee> getChildren(Employee parentEmployee) {
        employeeChildren.clear();
        for (Employee employee : employeeList) {
            if (employee.getChef() == parentEmployee.getId()) {
                employeeChildren.add(employee);
            }
        }
        return employeeChildren;
    }

}
