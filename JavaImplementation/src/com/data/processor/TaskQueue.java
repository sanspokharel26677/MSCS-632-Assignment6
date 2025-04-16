/*
 * TaskQueue.java
 * --------------
 * A thread-safe queue that stores tasks to be processed by worker threads.
 * Uses synchronized blocks to manage safe access between multiple threads.
 */

 package com.data.processor;

 import java.util.LinkedList;
 import java.util.Queue;
 
 public class TaskQueue {
     private final Queue<Task> queue;
 
     // Constructor initializes the queue
     public TaskQueue() {
         this.queue = new LinkedList<>();
     }
 
     /*
      * Adds a new task to the queue in a thread-safe manner.
      */
     public synchronized void addTask(Task task) {
         queue.add(task);
         notify(); // Notify waiting threads that a new task is available
     }
 
     /*
      * Retrieves and removes a task from the queue.
      * Waits if the queue is empty.
      */
     public synchronized Task getTask() throws InterruptedException {
         while (queue.isEmpty()) {
             wait(); // Wait until a task is available
         }
         return queue.poll();
     }
 
     /*
      * Checks if the queue is empty (non-blocking).
      */
     public synchronized boolean isEmpty() {
         return queue.isEmpty();
     }
 }