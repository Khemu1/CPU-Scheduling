package algos;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import utils.Utils;

public class RR {

    /**
     * Executes the Round Robin scheduling algorithm for a list of processes.
     *
     * @param processList        An ObservableList of processes to be scheduled.
     * @param executionOrderList An ObservableList to keep track of the execution order of processes.
     * @param timeQuantum        The time quantum for the Round Robin scheduling algorithm.
     */
    public static void runRoundRobin(ObservableList<Process> processList, ObservableList<ExecutionOrder> executionOrderList, int timeQuantum) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                int currentTime = 0;

                if (processList.isEmpty()) {
                    return null;
                }

                // Create a queue for the processes
                ObservableList<Process> readyQueue = javafx.collections.FXCollections.observableArrayList(processList);

                while (!readyQueue.isEmpty()) {
                    // Iterate through the ready queue
                    for (int i = 0; i < readyQueue.size(); ) {
                        Process process = readyQueue.get(i);

                        // Set process status to "Running" and update UI
                        process.setStatus("Running");
                        Utils.updateUI(process);

                        int burstTime = process.getCpuTime();
                        int executionTime = Math.min(burstTime, timeQuantum);

                        // Simulate execution for the time quantum or remaining burst time
                        Utils.sleepWithCatch(2000); // Simulate the process running
                        currentTime += executionTime;

                        // Update remaining burst time
                        burstTime -= executionTime;
                        process.setCpuTime(burstTime); // Update the burst time directly in the process

                        if (burstTime > 0) {
                            // Process not finished, update its status to "Ready"
                            process.setStatus("Ready");
                            Utils.updateUI(process);
                            i++; // Move to the next process in the queue
                        } else {
                            // Process finished execution
                            process.setStatus("Completed");
                            Utils.updateUI(process);
                            process.setCompletionTime(currentTime);

                            int turnaroundTime = currentTime - process.getArrivalTime();
                            int waitingTime = turnaroundTime - process.getCpuTime();

                            process.setTurnaroundTime(turnaroundTime);
                            process.setWaitingTime(waitingTime);

                            // Add to execution order list
                            ExecutionOrder executionOrder = new ExecutionOrder(
                                currentTime,
                                process.getProcessNumber(),
                                process.getArrivalTime(),
                                process.getCpuTime()
                            );
                            executionOrderList.add(executionOrder);

                            // Remove the process from the queue
                            readyQueue.remove(i);
                        }
                    }
                }

                return null;
            }
        };

        new Thread(task).start();
    }
}
