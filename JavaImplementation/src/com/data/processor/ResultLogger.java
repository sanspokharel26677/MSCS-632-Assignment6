/*
 * ResultLogger.java
 * -----------------
 * A thread-safe logger class responsible for logging processed task results
 * to a shared resource (in this case, an output text file). It handles
 * file I/O exceptions gracefully and logs thread activity as well.
 */

 package com.data.processor;

 import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 public class ResultLogger {
     private final String outputFile;
 
     // Constructor that accepts the file path where results will be logged
     public ResultLogger(String outputFile) {
         this.outputFile = outputFile;
 
         // Clear old contents if file exists
         try (PrintWriter pw = new PrintWriter(outputFile)) {
             pw.print(""); // Empty the file
         } catch (IOException e) {
             System.err.println("Failed to clear previous log file: " + e.getMessage());
         }
     }
 
     /*
      * Logs a message to the output file in a thread-safe manner.
      * This method is synchronized to prevent write collisions.
      */
     public synchronized void log(String message) {
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
             writer.write(message);
             writer.newLine();
         } catch (IOException e) {
             System.err.println("Logging error: " + e.getMessage());
         }
     }
 }