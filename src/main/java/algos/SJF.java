package algos;

import java.util.Comparator;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import utils.Utils;

public class SJF {

    /**
     * Tries to Executes the Shortest Job First (SJF) scheduling for a list of
     * processes. If all the CPU times are equal, it will use the FCFS scheduling
     * and updates the UI to reflect the current status of processes.
     *
     * @param processList        An ObservableList of processes to be scheduled.
     * @param executionOrderList An ObservableList to keep track of the execution
     * 
     */
    public static void shortestJobFirst(ObservableList<Process> processList,
            ObservableList<ExecutionOrder> executionOrderList) {

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws InterruptedException {

                processList.sort(Comparator.comparingInt(Process::getCpuTime)
                        .thenComparingInt(Process::getArrivalTime)); // Sort by CPU time, then arrival time

                scheduleProcesses(processList, executionOrderList);
                return null;
            }
        };

        new Thread(task).start();
    }

    private static void scheduleProcesses(ObservableList<Process> processList,
            ObservableList<ExecutionOrder> executionOrderList) throws InterruptedException {

        int currentTime = 0;
        int avg = processList.size() / 2; // You may want to adjust how avg is calculated here

        for (Process process : processList) {
            int arrivalTime = process.getArrivalTime();

            // If currentTime is less than arrival time, set current time to arrival time
            if (currentTime < arrivalTime) {
                currentTime = arrivalTime;
            }

            process.setStatus("Running");
            Utils.updateUI(process);

            // Simulate process running by sleeping for a while
            Utils.sleepWithCatch(2000);

            // Increase currentTime by the CPU time (burst time)
            currentTime += process.getCpuTime();

            // Update the process' timing
            Utils.updateProcessTiming(process, currentTime);

            // Calculate turnaround and waiting times
            int turnaroundTime = currentTime - arrivalTime;
            int waitingTime = turnaroundTime - process.getCpuTime(); // Waiting time = Turnaround - Burst time

            process.setTurnaroundTime(turnaroundTime);
            process.setWaitingTime(waitingTime);

            // Check if the process index is the average (this part can be customized as per
            // requirement)
            if (arrivalTime == avg) {
                process.setStatus("Waiting");
                Utils.sleepWithCatch(2000);
                process.setStatus("Ready");
            }

            // Final state after the process completes
            Utils.sleepWithCatch(2000);
            process.setStatus("Completed");

            // Create an execution order object
            ExecutionOrder executionOrder = new ExecutionOrder(currentTime, process.getProcessNumber(),
                    arrivalTime, process.getCpuTime());
            executionOrderList.add(executionOrder);
        }
    }
}
