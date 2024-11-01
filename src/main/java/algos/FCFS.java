package algos;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import utils.Utils;

import java.util.Comparator;

public class FCFS {

    /**
     * Runs the First-Come, First-Served (FCFS) scheduling algorithm.
     * The algo works as normal queue it executes based in entered order
     *
     * @param processList        An ObservableList of processes to be scheduled.
     * @param executionOrderList An ObservableList to keep track of the execution order of processes.
     */

    public static void runFCFS(ObservableList<Process> processList, ObservableList<ExecutionOrder> executionOrderList) {

        processList.sort(Comparator.comparingInt(Process::getArrivalTime));

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                int currentTime = 0; // Initialize current time

                for (Process process : processList) {
                    if (currentTime < process.getArrivalTime()) {
                        currentTime = process.getArrivalTime();
                    }

                    process.setStatus("Running");
                    Utils.updateUI(process);

                    Thread.sleep(1000);

                    currentTime += process.getCpuTime();

                    Utils.updateProcessTiming(process, currentTime);

                    ExecutionOrder executionOrder = new ExecutionOrder(currentTime, process.getProcessNumber(), process.getArrivalTime());
                    executionOrderList.add(executionOrder);
                }
                return null;
            }

        };

        new Thread(task).start();
    }

}
