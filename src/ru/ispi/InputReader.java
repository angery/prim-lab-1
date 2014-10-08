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
public class InputReader extends Thread {

    BlockingQueue bq;
    int[] arr;
    Counter readCounter;

    public InputReader(int[] input, BlockingQueue bq, Counter readCounter) {
        this.bq = bq;
        this.arr = input;
        this.readCounter = readCounter;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (readCounter) {
                if (readCounter.getValue() >= arr.length)
                    break;
                try {
                    bq.add(arr[readCounter.getValue()]);
                    readCounter.increment();
                    Thread.currentThread().sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
