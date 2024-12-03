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
                int currentTime = 0; // Keeps track of the current time

                if (processList.isEmpty()) {
                    return null; // Exit if there are no processes
                }

                // Initialize the ready queue with all the processes
                ObservableList<Process> readyQueue = javafx.collections.FXCollections.observableArrayList(processList);

                while (!readyQueue.isEmpty()) {
                    // Temporary queue to hold processes for the next iteration
                    ObservableList<Process> tempQueue = javafx.collections.FXCollections.observableArrayList();

                    for (Process process : readyQueue) {
                        // Set the process status to "Running" and update the UI
                        process.setStatus("Running");
                        Utils.updateUI(process);

                        // Get the remaining CPU burst time and calculate the execution time
                        int burstTime = process.getCpuTime();
                        int executionTime = Math.min(burstTime, timeQuantum);

                        // Simulate execution for the time quantum or remaining burst time
                        Utils.sleepWithCatch(2000); // Simulate process execution
                        currentTime += executionTime;

                        // Decrease the remaining burst time
                        burstTime -= executionTime;

                        if (burstTime > 0) {
                            // Process is not finished, update its CPU time and requeue it
                            process.setCpuTime(burstTime);
                            process.setStatus("Ready");
                            Utils.updateUI(process); // Update the UI for the Ready status
                            tempQueue.add(process); // Add back to the queue
                        } else {
                            // Process finished execution
                            process.setStatus("Completed");
                            Utils.updateUI(process);

                            // Calculate turnaround time and waiting time
                            int turnaroundTime = currentTime - process.getArrivalTime();
                            int waitingTime = turnaroundTime - process.getOriginalCpuTime(); // Use original burst time for waiting time

                            process.setTurnaroundTime(turnaroundTime);
                            process.setWaitingTime(waitingTime);

                            // Record the execution order
                            ExecutionOrder executionOrder = new ExecutionOrder(
                                currentTime,
                                process.getProcessNumber(),
                                process.getArrivalTime(),
                                process.getOriginalCpuTime() // Original burst time
                            );
                            executionOrderList.add(executionOrder);
                        }
                    }

                    // Clear the ready queue and prepare for the next iteration
                    readyQueue.clear();
                    readyQueue.addAll(tempQueue);
                }

                return null; // Task is complete
            }
        };

        // Start the task in a new thread
        new Thread(task).start();
    }
}
