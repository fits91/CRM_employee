/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.swingclient;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author fits-dev
 */
public class MainWindow extends JFrame {

    public static Container mainContainer;

    public MainWindow() {
        setTitle("Admin panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());
        setVisible(true);
        try {
            mainContainer.add(new EmployeeTreePanel(), BorderLayout.WEST);
            mainContainer.add(new EmployeeTablePanel().getMain(), BorderLayout.CENTER);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainContainer, "Ошибка соединения с сервером");
        }
    }

}
