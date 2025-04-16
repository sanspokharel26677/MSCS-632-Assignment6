/*
 * Data Processing System - Java Version
 * -------------------------------------
 * This program simulates a multi-threaded data processing system where multiple worker threads
 * retrieve tasks from a synchronized shared queue, process them (with simulated delays), and
 * log the result into a shared resource (a log file in this case). The system demonstrates
 * synchronization, exception handling, and clean thread management using ExecutorService.
 */

 package com.data.processor;

 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.List;
 import java.util.ArrayList;
 
 public class Main {
 
     /*
      * Main method to set up and start the data processing system.
      * Initializes the shared queue, adds tasks, sets up logging, and starts worker threads.
      */
     public static void main(String[] args) {
         // Number of worker threads
         final int NUM_WORKERS = 5;
 
         // Create the shared task queue
         TaskQueue taskQueue = new TaskQueue();
 
         // Add sample tasks to the queue
         for (int i = 1; i <= 20; i++) {
             taskQueue.addTask(new Task(i, "Data-" + i));
         }
 
         // Create a shared logger (simulates writing to a result file)
         ResultLogger resultLogger = new ResultLogger("processed_results.txt");
 
         // Create a fixed-size thread pool
         ExecutorService executor = Executors.newFixedThreadPool(NUM_WORKERS);
 
         // Create and submit worker threads
         for (int i = 1; i <= NUM_WORKERS; i++) {
             executor.submit(new Worker("Worker-" + i, taskQueue, resultLogger));
         }
 
         // Shutdown executor after task completion
         executor.shutdown();
 
         // Wait until all tasks are done
         while (!executor.isTerminated()) {
             // Busy-wait until all threads finish
         }
 
         System.out.println("All tasks processed. Check the output file for results.");
     }
 }