/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ispi;

/**
 *
 * @author gerasimov.pk
 */
public class Counter {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public void increment() {
        value ++;
    }
    
    public void decrement() {
        value --;
    }
}
