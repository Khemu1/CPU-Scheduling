package algos;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import utils.Utils;

import java.util.Comparator;

public class Priority {

    /**
     * Executes the Priority non-preemptive Scheduling for a list of processes.
     * The processes are first sorted by priority, then executed using FCFS logic.
     *
     * @param processList        An ObservableList of processes to be scheduled.
     * @param executionOrderList An ObservableList to keep track of the execution order of processes.
     */
    public static void runPriority(ObservableList<Process> processList, ObservableList<ExecutionOrder> executionOrderList) {

        // Sort the process list by priority (lower value = higher priority)
        processList.sort(Comparator.comparingInt(Process::getPriority));

        // Use the same FCFS logic to execute processes
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws InterruptedException {
                int currentTime = 0; // Initialize the current time for scheduling

                for (Process process : processList) {
                    // Adjust the current time if necessary
                    if (currentTime < process.getArrivalTime()) {
                        currentTime = process.getArrivalTime();
                    }

                    // Update process status to "Running" and update the UI
                    process.setStatus("Running");
                    Utils.updateUI(process);

                    // Simulate process execution
                    Utils.sleepWithCatch(2000);

                    // Update current time after the process is completed
                    currentTime += process.getCpuTime();

                    // Calculate and set turnaround and waiting times
                    process.setCompletionTime(currentTime);
                    process.setTurnaroundTime(currentTime - process.getArrivalTime());
                    process.setWaitingTime(process.getTurnaroundTime() - process.getCpuTime());

                    // Update process status to "Completed" and update the UI
                    process.setStatus("Completed");
                    Utils.updateUI(process);

                    // Add to execution order list
                    ExecutionOrder executionOrder = new ExecutionOrder(
                            currentTime,
                            process.getProcessNumber(),
                            process.getArrivalTime(),
                            process.getCpuTime()
                    );
                    executionOrderList.add(executionOrder);
                }

                return null; // Task completed
            }
        };

        // Start the task in a new thread to avoid blocking the UI
        new Thread(task).start();
    }
}
