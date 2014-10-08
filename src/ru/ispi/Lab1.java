/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ispi;

import java.util.Random;

/**
 *
 * @author gerasimov.pk
 */
public class Lab1 {

    private static int DEFAULT_ARR_LENGTH = 100;
    private static int DEFAULT_QUEUE_LENGTH = 10;
    private static int DEFAULT_THREADS_COUNT = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Counter readCounter = new Counter();
        Counter writeCounter = new Counter();

        int arrLength;
        if (args.length > 0 && args[0] != null) {
            arrLength = Integer.parseInt(args[0]);
        } else {
            arrLength = DEFAULT_ARR_LENGTH;
        }
        int queueLength;
        if (args.length > 0 && args[1] != null) {
            queueLength = Integer.parseInt(args[1]);
        } else {
            queueLength = DEFAULT_QUEUE_LENGTH;
        }
        int threadsCount;
        if (args.length > 0 && args[1] != null) {
            threadsCount = Integer.parseInt(args[2]);
        } else {
            threadsCount = DEFAULT_THREADS_COUNT;
        }

        int[] input = new int[arrLength];
        int[] output = new int[arrLength];

        Random r = new Random();

        int sum1 = 0;
        for (int i = 0; i < arrLength; i++) {
            input[i] = r.nextInt();
            sum1 += input[i];
        }
        System.out.println(sum1);
        
        BlockingQueue bq = new BlockingQueue(queueLength);

        Thread[] threads = new Thread[threadsCount*2];
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new InputReader(input, bq, readCounter);
            threads[i].start();            
        }
        for (int j = threadsCount; j < threadsCount*2; j++) {
            threads[j] = new OutputWriter(output, bq, writeCounter);
            threads[j].start();  
        }
        for (Thread thread : threads) {
            thread.join();
        }
        
        int sum2 = 0;
        for (int i = 0; i < arrLength; i++) {
            sum2 += output[i];
        }
        System.out.println(sum2);
    }

}
