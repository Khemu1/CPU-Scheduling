package algos;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import utils.Utils;

public class RR {

    /**
     * Executes the Round Robin scheduling algorithm for a list of processes.
     * This method provides the structure for handling the Round Robin logic and
     * updates the UI to reflect the current status of processes.
     *
     * @param processList        An ObservableList of processes to be scheduled.
     * @param executionOrderList An ObservableList to keep track of the execution order of processes.
     * @param timeQuantum        Write something.
     *
     */

    // feel free to add any params and don't forget edit the calling the Controller
    public static void runRoundRobin(ObservableList<Process> processList, ObservableList<ExecutionOrder> executionOrderList, int timeQuantum) {

        // Create a background task to run the Round Robin scheduling
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                int currentTime = 0; // Initialize the current time for scheduling

                // Process scheduling logic will go here
                // Make sure to update the UI using Utils.updateUI(process) when changing the process status.

                // Example structure for Round Robin implementation
                /*
                while (!processList.isEmpty()) {
                    for (Process process : processList) {
                        // Check if the process is ready to run
                        // If it is ready, set the status to 'Running'
                        // Update the UI and process status by calling Utils.updateUI(process)

                        // Simulate running the process for the timeQuantum
                        // You may need to adjust the remaining CPU time for the process accordingly

                        // Update timing and check if the process is complete by calling Utils.updateProcessTiming(process)

                        // If the process is complete, remove it from processList and add to executionOrderList
                        // If not complete, add it back to the processList to run in the next round
                    }
                }
                */


                return null; // Return null when the task is complete
            }
        };

        // Start the task in a new thread to avoid blocking the UI
        // don't touch it
        new Thread(task).start();
    }
}
