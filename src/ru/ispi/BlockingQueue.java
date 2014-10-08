/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ispi;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author gerasimov.pk
 */
public class BlockingQueue {

    private List queue = new LinkedList();
    private int  limit = 10;

    public BlockingQueue(int limit){
        this.limit = limit;
    }


    public synchronized void add(int item) throws InterruptedException  {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }


    public synchronized int get() throws InterruptedException{
        while (this.queue.size() == 0){
           wait();
        }
        if (this.queue.size() == this.limit){
            notifyAll();
        }

        return (int) this.queue.remove(0);
    }
}