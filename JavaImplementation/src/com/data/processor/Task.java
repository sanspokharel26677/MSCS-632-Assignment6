/*
 * Task.java
 * ---------
 * Represents a single unit of work for the worker threads.
 * Each task has an ID and some payload (simulated here as a string).
 */

 package com.data.processor;

 public class Task {
     private final int taskId;
     private final String payload;
 
     // Constructor
     public Task(int taskId, String payload) {
         this.taskId = taskId;
         this.payload = payload;
     }
 
     // Returns task ID
     public int getTaskId() {
         return taskId;
     }
 
     // Returns task payload
     public String getPayload() {
         return payload;
     }
 
     /*
      * Simulates processing of the task.
      * Includes a delay to mimic computational work.
      */
     public String process() throws InterruptedException {
         // Simulate computation delay
         Thread.sleep((long) (Math.random() * 1000 + 500));
         return "Processed " + payload;
     }
 }