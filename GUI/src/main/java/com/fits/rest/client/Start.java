/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.rest.client;

import com.fits.swingclient.MainWindow;
import java.awt.EventQueue;

/**
 *
 * @author fits-dev
 */
public class Start {

    public static void main(String[] args) {

        String url = "http://localhost:8084/Web"; // тут указать путь к серверу
        FactoryBuilder.setEs(new EmployeeRestClient(url));

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }
}
