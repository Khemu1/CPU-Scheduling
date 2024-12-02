package utils;

import algos.Process;
import javafx.application.Platform;

public class Utils {

    /**
     * Updates the timing information for a given process.
     *
     * @param process     The process to update.
     * @param currentTime The current time after the process runs.
     * @throws InterruptedException If the thread is interrupted during sleep.
     */
    public static void updateProcessTiming(Process process, int currentTime) throws InterruptedException {
        process.setCompletionTime(currentTime);
        process.setTurnaroundTime(process.getCompletionTime() - process.getArrivalTime());
        process.setWaitingTime(process.getTurnaroundTime() - process.getCpuTime());
    }

    /**
     * Updates the UI with the current status of the process.
     *
     * @param process The process whose status is being updated.
     */
    public static void updateUI(Process process) {
        // Update the UI on the JavaFX Application Thread
        Platform.runLater(() -> {
            System.out.println("Process " + process.getProcessNumber() + " status updated to " + process.getStatus());
        });
    }
    
    /**
     * Sleeps for a given amount of time.
     * this method is used to catch the interrupted exception
     * @param millis
     */
    public static void sleepWithCatch(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
