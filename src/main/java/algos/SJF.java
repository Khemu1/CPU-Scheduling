package algos;

import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import utils.Utils;

public class SJF {

    /**
     * Tries to Executes the Shortest Job First (SJF) scheduling for a list of
     * processes.
     * if all the cpu times are equal then it will use the FCFS scheduling
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
                        .thenComparingInt(Process::getArrivalTime));

                scheduleProcesses(processList, executionOrderList);
                return null;
            }
        };

        new Thread(task).start();
    }

    private static void scheduleProcesses(ObservableList<Process> processList,
            ObservableList<ExecutionOrder> executionOrderList) throws InterruptedException {

        int currentTime = 0;
        int avg = processList.size() / 2;

        for (Process process : processList) {
            int currentIndex = process.getArrivalTime();

            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();
            }

            process.setStatus("Running");
            Utils.updateUI(process);

            Utils.sleepWithCatch(2000);
            currentTime += process.getCpuTime();

            Utils.updateProcessTiming(process, currentTime);

            int turnaroundTime = currentTime - process.getArrivalTime();
            int waitingTime = turnaroundTime - process.getCpuTime();

            process.setTurnaroundTime(turnaroundTime);
            process.setWaitingTime(waitingTime);

            if (currentIndex == avg) {
                process.setStatus("Waiting");
                Utils.sleepWithCatch(2000);
                process.setStatus("Ready");
            }

            Utils.sleepWithCatch(2000);
            process.setStatus("Running");

            Utils.sleepWithCatch(2000);

            process.setStatus("Completed");

            ExecutionOrder executionOrder = new ExecutionOrder(currentTime, process.getProcessNumber(),
                    process.getArrivalTime(), process.getCpuTime());
            executionOrderList.add(executionOrder);
        }
    }
}
