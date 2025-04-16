/*
=========================================
Go Data Processing System
=========================================
This Go program simulates a multi-threaded data processing system using goroutines and channels.
It creates a set of worker goroutines that pull tasks from a shared task channel, process them
(simulated with a delay), and log the result into an output file.

Concurrency is handled using channels for task distribution and a mutex for synchronized file writing.
Graceful shutdown and error handling are also implemented to ensure thread-safe execution.
*/

package main

import (
	"fmt"
	"sync"
)

// Number of workers and tasks (can be adjusted)
const numWorkers = 5
const numTasks = 20

func main() {
	fmt.Println("📦 Starting Go Data Processing System...")

	// Create a buffered task queue (channel)
	taskQueue := make(chan Task, numTasks)

	// Create a wait group to wait for all workers to finish
	var wg sync.WaitGroup

	// Create a shared logger instance
	logger := NewLogger("go_processed_results.txt")

	// Start worker goroutines
	for i := 1; i <= numWorkers; i++ {
		workerName := fmt.Sprintf("Worker-%d", i)
		wg.Add(1)
		go Worker(workerName, taskQueue, &wg, logger)
	}

	// Add tasks to the queue
	for i := 1; i <= numTasks; i++ {
		task := Task{
			ID:      i,
			Payload: fmt.Sprintf("Data-%d", i),
		}
		taskQueue <- task
	}

	// Close the task queue so workers stop after consuming all tasks
	close(taskQueue)

	// Wait for all workers to complete
	wg.Wait()

	fmt.Println("✅ All tasks processed. Check 'go_processed_results.txt' for output.")
}