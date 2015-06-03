/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.model;

import com.fits.swingclient.EmployeeTreePanel;
import com.fits.swingclient.MainWindow;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author fits-dev
 */
public class AddNewPanel {

    /**
     * change panel in GUI
     *
     * @param panel painted panel
     */
    public static synchronized void changePanel(JPanel panel) {
        MainWindow.mainContainer.remove(1);
        MainWindow.mainContainer.add(panel, BorderLayout.CENTER);
        MainWindow.mainContainer.revalidate();
        MainWindow.mainContainer.repaint();
    }

    /**
     * repaint TreeModel and Panel
     *
     * @param panel painted panel
     */
    public static synchronized void repaintTree(JPanel panel) {
        MainWindow.mainContainer.remove(1);
        MainWindow.mainContainer.remove(0);
        MainWindow.mainContainer.add(new EmployeeTreePanel(), BorderLayout.WEST);
        MainWindow.mainContainer.revalidate();
        MainWindow.mainContainer.repaint();
        MainWindow.mainContainer.add(panel, BorderLayout.CENTER);
        MainWindow.mainContainer.revalidate();
        MainWindow.mainContainer.repaint();
    }

}
