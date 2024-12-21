package algos;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import utils.Utils;

import java.util.Comparator;

public class FCFS {

    /**
     * Runs the First-Come, First-Served (FCFS) scheduling algorithm.
     * The algo works as a normal queue it executes based in entered order
     *
     * @param processList        An ObservableList of processes to be scheduled.
     * @param executionOrderList An ObservableList to keep track of the execution
     *                           order of processes.
     */

    public static void runFCFS(ObservableList<Process> processList, ObservableList<ExecutionOrder> executionOrderList) {

        processList.sort(Comparator.comparingInt(Process::getArrivalTime));
        int avg = processList.size() / 2;
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                int currentTime = 0;

                if (processList.isEmpty()) {
                    return null;
                }

                for (Process process : processList) {
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


                    if (process.getArrivalTime() == avg) {
                        process.setStatus("Waiting");
                        Utils.sleepWithCatch(2000);
                        process.setStatus("Ready");
                    }


                    Utils.sleepWithCatch(2000);
                    process.setStatus("Completed");

                    ExecutionOrder executionOrder = new ExecutionOrder(currentTime, process.getProcessNumber(),
                            process.getArrivalTime(), process.getCpuTime());

                    executionOrderList.add(executionOrder);
                }
                return null;
            }
        };

        new Thread(task).start();
    }

}
