/*
=========================================
task.go
=========================================
Defines the Task structure used by the data processing system.
Each task has an ID and a payload string. The Process() method simulates work
with a random delay and returns the result string.
*/

package main

import (
	"fmt"
	"math/rand"
	"time"
)

// Task struct represents a unit of work with ID and Payload
type Task struct {
	ID      int
	Payload string
}

// Process simulates processing of a task with a delay
func (t Task) Process() string {
	// Simulate work delay between 500ms to 1500ms
	delay := time.Duration(rand.Intn(1000)+500) * time.Millisecond
	time.Sleep(delay)

	// Return processed result
	return fmt.Sprintf("Processed %s", t.Payload)
}