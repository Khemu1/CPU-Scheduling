package algos;

public class ExecutionOrder {
    private final int completionTime;
    private final String processNumber;
    private final int arrivalTime;
    private final int turnaroundTime;
    private final int waitingTime;

    public ExecutionOrder(int completionTime, String processNumber, int arrivalTime, int burstTime) {
        this.completionTime = completionTime;
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.turnaroundTime = completionTime - arrivalTime;
        this.waitingTime = turnaroundTime - burstTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public String getProcessNumber() {
        return processNumber;
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
}
