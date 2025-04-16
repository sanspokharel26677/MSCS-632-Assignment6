/*
=========================================
worker.go
=========================================
Defines the worker function that runs as a goroutine. Each worker listens to the
shared task queue (channel), processes tasks using the Task struct's Process method,
handles any unexpected panics, and logs results using the shared Logger.
*/

package main

import (
	"fmt"
	"sync"
)

// Worker function runs as a goroutine, processes tasks from the taskQueue, and logs results
func Worker(name string, taskQueue <-chan Task, wg *sync.WaitGroup, logger *Logger) {
	// Ensure WaitGroup is marked as done at the end
	defer wg.Done()

	// Recover from panic if something goes wrong
	defer func() {
		if r := recover(); r != nil {
			logger.Log(fmt.Sprintf("%s crashed with error: %v", name, r))
		}
	}()

	// Log worker start
	logger.Log(fmt.Sprintf("%s started.", name))

	for task := range taskQueue {
		// Process task (simulated delay inside)
		result := task.Process()

		// Log the result
		logger.Log(fmt.Sprintf("%s completed Task-%d: %s", name, task.ID, result))
	}

	// Log worker finish
	logger.Log(fmt.Sprintf("%s finished.", name))
}