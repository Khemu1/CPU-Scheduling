package algos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Process {
    /**
     * unique identifier for the process.
     */
    private final String processNumber;

    /**
     * total CPU time required for the process to execute.
     * This value represents the burst time needed by the process.
     */
    private final int cpuTime;

    /**
     * priority level of the process.
     */
    private final int priority;

    /**
     * status of the process, represented as a StringProperty.
     * opted for StringProperty to since i'm since i need to make the change be reflected in the UI
     * This field can reflect different states such as "Ready", "Running", or "Completed".
     */
    private final StringProperty status;

    /**
     * time at which the process arrives in the ready queue.
     *
     */
    private final int arrivalTime;

    /**
     * time at which the process completes its execution.
     */
    private int completionTime;

    /**
     * total time taken from arrival to completion.
     */
    private int turnaroundTime;

    /**
     * total time the process has been waiting in the ready queue.
     */
    private int waitingTime;

    /**
     * @param processNumber The unique identifier for the process.
     * @param cpuTime The total CPU time required for the process execution (burst time).
     * @param priority The priority level of the process (lower values indicate higher priority).
     * @param arrivalTime The time at which the process arrives in the ready queue.
     */
    public Process(String processNumber, int cpuTime, int priority, int arrivalTime) {
        this.processNumber = processNumber;
        this.cpuTime = cpuTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.status = new SimpleStringProperty("Ready");
    }

    // getters
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
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
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

    // setters
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
        this.status.set(status);
    }
}
