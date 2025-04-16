# 🧠 MSCS-632-Assignment6 / Data Processing System (Java & Go)

This project simulates a multi-threaded **Data Processing System** implemented in both **Java** and **Go** for educational purposes. Multiple worker threads (or goroutines) retrieve tasks from a shared queue, process them with simulated delays, and log results to a shared output file. 

This assignment demonstrates key concepts in **concurrency**, **synchronization**, **thread/goroutine management**, **exception handling**, and **file I/O logging**.

---

## 📁 Project Structure

├── JavaImplementation/
│   ├── src/com/data/processor/
│   │   ├── Main.java
│   │   ├── Task.java
│   │   ├── TaskQueue.java
│   │   ├── Worker.java
│   │   └── ResultLogger.java
│   └── processed_results.txt
│
├── GoImplementation/
│   ├── main.go
│   ├── task.go
│   ├── logger.go
│   └── worker.go
│   └── go_processed_results.txt
│
├── README.md

---

## 🚀 How to Run

### ✅ Java Version

#### 🔧 Prerequisites
- Java 8 or above
- Any IDE (e.g., IntelliJ, Eclipse) or terminal

#### 🛠️ Steps
1. Navigate to the `JavaImplementation/` folder.
2. Open `src/com/data/processor/` in your IDE.
3. Compile and run `Main.java`.

#### 📤 Output
- A file named `processed_results.txt` will be generated.
- It will contain logs from all worker threads, task IDs, and processing messages.

---

### ✅ Go Version

#### 🔧 Prerequisites
- Go 1.16 or above installed
- Terminal access

#### 🛠️ Steps
1. Open terminal and navigate to the `GoImplementation/` directory.
2. Initialize module (first time only):
   ```bash
   go mod init go-data-processor```
3. go run .

📤 Output
	•	A file named go_processed_results.txt will be created.
	•	It contains logs of goroutines starting, processing tasks, and finishing.

## Features Implemented
	•	Shared task queue using synchronized (Java) and channels (Go)
	•	Worker threads/goroutines that simulate processing with random delays
	•	Thread-safe logging with mutex in both versions
	•	Proper exception/panic handling (e.g., InterruptedException, file I/O, etc.)
	•	Graceful shutdown and proper resource cleanup


## 📚 Technologies Used

| Feature              | Java Implementation            | Go Implementation                |
|----------------------|-------------------------------|----------------------------------|
| Concurrency Model    | `ExecutorService`, Threads     | Goroutines + Channels            |
| Task Queue           | Custom `TaskQueue` + sync      | Buffered Channel                 |
| Logging              | FileWriter + `synchronized`    | `os.File` + `sync.Mutex`         |
| Error Handling       | Try-Catch                      | Error checking + Defer + Recover |
| Output File          | `processed_results.txt`        | `go_processed_results.txt`       |


## Sample Output Preview

Worker-3 started.
Worker-3 completed Task-6: Processed Data-6
Worker-3 finished.
...

## Author Notes
Both versions are designed for academic demonstration and clarity.
