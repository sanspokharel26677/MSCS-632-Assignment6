/*
 * Worker.java
 * -----------
 * A class that implements Runnable and represents a worker thread.
 * Each worker pulls tasks from the shared queue, processes them,
 * handles potential exceptions, and logs the results using ResultLogger.
 */

 package com.data.processor;

 public class Worker implements Runnable {
     private final String workerName;
     private final TaskQueue taskQueue;
     private final ResultLogger logger;
 
     // Constructor that accepts a name, the shared task queue, and the shared logger
     public Worker(String workerName, TaskQueue taskQueue, ResultLogger logger) {
         this.workerName = workerName;
         this.taskQueue = taskQueue;
         this.logger = logger;
     }
 
     /*
      * The main logic of the thread. It continuously pulls tasks
      * from the queue, processes them, and logs results or errors.
      */
     @Override
     public void run() {
         logger.log(workerName + " started.");
 
         while (true) {
             Task task;
 
             try {
                 // Retrieve a task from the shared queue
                 task = taskQueue.getTask();
             } catch (InterruptedException e) {
                 logger.log(workerName + " interrupted while waiting for task.");
                 return;
             }
 
             try {
                 // Simulate task processing
                 String result = task.process();
 
                 // Log the result
                 logger.log(workerName + " completed Task-" + task.getTaskId() + ": " + result);
 
             } catch (InterruptedException e) {
                 logger.log(workerName + " interrupted during processing of Task-" + task.getTaskId());
             } catch (Exception e) {
                 // Catch any unexpected processing errors
                 logger.log(workerName + " encountered an error processing Task-" + task.getTaskId() + ": " + e.getMessage());
             }
 
             // Optional stop condition if queue is empty (for cleaner shutdown)
             synchronized (taskQueue) {
                 if (taskQueue.isEmpty()) break;
             }
         }
 
         logger.log(workerName + " finished.");
     }
 }