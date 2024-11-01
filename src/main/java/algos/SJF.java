package algos;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class SJF {

    /**
     * Executes the Shortest Job First (SJF) scheduling for a list of processes.
     * This method provides the structure for handling the SJF scheduling logic
     * and updates the UI to reflect the current status of processes.
     *
     * @param processList        An ObservableList of processes to be scheduled.
     * @param executionOrderList An ObservableList to keep track of the execution order of processes.
     *
     * Additional parameters may be added as needed.
     */
    public static void shortestJobFirst(ObservableList<Process> processList, ObservableList<ExecutionOrder> executionOrderList) {

        // Create a background task to run the SJF scheduling
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                int currentTime = 0; // Initialize the current time for scheduling

                // Process scheduling logic will go here
                // Ensure to update the UI using Utils.updateUI(process) when changing the process status.

                // Example structure for SJF scheduling implementation
                /*
                while (!processList.isEmpty()) {
                    // Sort processList based on CPU time (shortest job first)
                    // Iterate over each process in the sorted processList
                    for (Process process : processList) {
                        // Check if the process is ready to run
                        // If it is ready, set the status to 'Running'
                        // Update the UI and process status by calling Utils.updateUI(process)

                        // Simulate running the process for its required CPU time
                        // Update the currentTime and process timing using Utils.updateProcessTiming(process)

                        // If the process is complete, remove it from processList and add to executionOrderList
                        // If not complete, leave it in the processList for the next iteration
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
