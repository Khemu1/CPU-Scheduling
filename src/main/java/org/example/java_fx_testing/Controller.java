package org.example.java_fx_testing;

import algos.*;
import algos.Process;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    // Process TableView and its columns
    @FXML
    private TableView<Process> processTable;

    @FXML
    private TableColumn<Process, String> processNumberColumn;
    @FXML
    private TableColumn<Process, Number> cpuTimeColumn;
    @FXML
    private TableColumn<Process, Number> priorityColumn;
    @FXML
    private TableColumn<Process, String> statusColumn;

    // Input fields for adding new processes
    @FXML
    private TextField processNumberField;
    @FXML
    private TextField cpuTimeField;
    @FXML
    private TextField priorityField;
    @FXML
    private TextField timeQuantumField;

    // Execution Order TableView and its columns
    @FXML
    private TableView<ExecutionOrder> executionOrderTable;

    @FXML
    private TableColumn<ExecutionOrder, String> executionOrderColumn;
    @FXML
    private TableColumn<ExecutionOrder, String> executionProcessColumn;
    @FXML
    private TableColumn<ExecutionOrder, Number> arrivalTimeColumn; // New Column

    // Observable list to hold processes
    private final ObservableList<Process> processList = FXCollections.observableArrayList();
    private final ObservableList<ExecutionOrder> executionOrderList = FXCollections.observableArrayList();
    private SortedList<ExecutionOrder> sortedExecutionOrderList;
    @FXML

    public void initialize() {
        // Initialize process table columns
        processNumberColumn.setCellValueFactory(new PropertyValueFactory<>("processNumber"));
        cpuTimeColumn.setCellValueFactory(new PropertyValueFactory<>("cpuTime"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Ensure your processTable is populated
        processTable.setItems(processList);
        setTableColumnProperties(processNumberColumn, cpuTimeColumn, priorityColumn, statusColumn);

        // Initialize execution order table columns
        executionOrderColumn.setCellValueFactory(new PropertyValueFactory<>("completionTime"));
        executionProcessColumn.setCellValueFactory(new PropertyValueFactory<>("processNumber"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

        // Ensure your execution order table is populated
        executionOrderTable.setItems(executionOrderList);
    }



    private void setTableColumnProperties(TableColumn<?, ?>... columns) {
        for (TableColumn<?, ?> column : columns) {
            column.setResizable(false);
            column.setReorderable(false);
            column.setSortable(false);
        }
    }

    @FXML
    private void handleAddProcessButtonAction() {
        String processNumber = processNumberField.getText();
        int cpuTime = Integer.parseInt(cpuTimeField.getText());
        int priority = Integer.parseInt(priorityField.getText());

        int arrivalTime = processList.size();

        Process newProcess = new Process(processNumber, cpuTime, priority, arrivalTime);

        processList.add(newProcess);

        // Clear the input fields
        processNumberField.clear();
        cpuTimeField.clear();
        priorityField.clear();
    }

    @FXML
    public void handleClearTable(ActionEvent actionEvent) {
        processList.clear();
        executionOrderList.clear();
        executionOrderTable.refresh();
    }

    @FXML
    public void handleRunFCFSButtonAction(ActionEvent actionEvent) throws InterruptedException {
        FCFS.runFCFS(processList, executionOrderList);
        processTable.refresh();
        executionOrderTable.refresh();
    }

    @FXML
    public void handleRunSJFButtonAction(ActionEvent actionEvent) {
         SJF.shortestJobFirst(processList,executionOrderList);

    }

    @FXML
    public void handleRunRRButtonAction(ActionEvent actionEvent) {
         RR.runRoundRobin(processList,executionOrderList,Integer.parseInt(timeQuantumField.getText()));
    }

    @FXML
    public void handleRunPriorityButtonAction(ActionEvent actionEvent) {
        Priority.runPriority(processList,executionOrderList);
    }
}
