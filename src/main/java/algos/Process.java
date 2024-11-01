package algos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Process {
    private final String processNumber; // Identifier for the process
    private final int cpuTime;           // CPU time required
    private final int priority;          // Priority level (for priority scheduling)
    private final StringProperty status;  // Status property as StringProperty
    private final int arrivalTime;       // Arrival time for FCFS
    private int completionTime;          // Completion time after scheduling
    private int turnaroundTime;          // Turnaround time for the process
    private int waitingTime;             // Waiting time for the process

    // Constructor
    public Process(String processNumber, int cpuTime, int priority, int arrivalTime) {
        this.processNumber = processNumber;
        this.cpuTime = cpuTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.status = new SimpleStringProperty("Ready"); // Initialize status
    }

    // Getters
    public String getProcessNumber() {
        return processNumber;
    }

    public int getCpuTime() {
        return cpuTime;
    }

    public int getPriority() {
        return priority;
    }

    public String getStatus() {
        return status.get(); // Get the current status
    }

    public StringProperty statusProperty() {
        return status; // Return the StringProperty for binding
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    // Setters
    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setStatus(String status) {
        this.status.set(status); // Set the status property
    }
}
