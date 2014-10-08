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
public class OutputWriter extends Thread {

    BlockingQueue bq;
    int[] arr;
    Counter writeCounter;

    public OutputWriter(int[] output, BlockingQueue bq, Counter writeCounter) {
        this.bq = bq;
        this.arr = output;
        this.writeCounter = writeCounter;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (writeCounter) {
                if (writeCounter.getValue() >= arr.length)
                    break;
                try {
                    arr[writeCounter.getValue()] = bq.get();
                    writeCounter.increment();
                    Thread.currentThread().sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
