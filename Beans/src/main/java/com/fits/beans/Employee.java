/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fits.beans;

import java.util.Objects;

/**
 *
 * @author fits-dev
 */
public class Employee {

    private int id;
    private String fio;
    private int chef;

    public Employee() {
    }

    public Employee(int id, String fio, int chef) {
        this.id = id;
        this.fio = fio;
        this.chef = chef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getChef() {
        return chef;
    }

    public void setChef(int chef) {
        this.chef = chef;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.fio);
        hash = 43 * hash + this.chef;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fio, other.fio)) {
            return false;
        }
        if (this.chef != other.chef) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fio=" + fio + ", chef=" + chef + '}';
    }
    
    
    
    

}
