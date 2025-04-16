/*
=========================================
logger.go
=========================================
Implements a thread-safe logger using a mutex to avoid race conditions during
concurrent writes. It writes each log entry to an output file and optionally
prints to console. Handles file I/O errors gracefully.
*/

package main

import (
	"fmt"
	"os"
	"sync"
)

// Logger struct contains file pointer and mutex for thread-safe logging
type Logger struct {
	file  *os.File
	mutex sync.Mutex
}

// NewLogger initializes the logger and clears any previous file content
func NewLogger(filename string) *Logger {
	// Truncate existing file or create a new one
	file, err := os.Create(filename)
	if err != nil {
		fmt.Printf("❌ Error creating log file: %v\n", err)
		os.Exit(1)
	}

	return &Logger{file: file}
}

// Log writes a log message to the file in a thread-safe manner
func (l *Logger) Log(message string) {
	l.mutex.Lock()
	defer l.mutex.Unlock()

	_, err := l.file.WriteString(message + "\n")
	if err != nil {
		fmt.Printf("❌ Error writing log: %v\n", err)
	}
}

// Close the file when done
func (l *Logger) Close() {
	l.file.Close()
}