package algos;

public class ExecutionOrder {
    private final int completionTime;
    private final String processNumber;
    private final int arrivalTime;

    public ExecutionOrder(int completionTime, String processNumber,int arrivalTime) {
        this.completionTime = completionTime;
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
    }

    // Getter for completionTime
    public int getCompletionTime() {
        return completionTime;
    }

    // Getter for processNumber
    public String getProcessNumber() {
        return processNumber;
    }
    public int getArrivalTime() { // New getter for arrival time
        return arrivalTime;
    }
}
