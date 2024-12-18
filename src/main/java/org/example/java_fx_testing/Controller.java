package org.example.java_fx_testing;

import algos.*;
import algos.Process;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    private TableView<Process> processTable;
    @FXML
    private TableColumn<Process, Number> processNumberColumn;
    @FXML
    private TableColumn<Process, Number> cpuTimeColumn;
    @FXML
    private TableColumn<Process, Number> priorityColumn;
    @FXML
    private TableColumn<Process, String> statusColumn;
    @FXML
    private TableColumn<Process, Number> arrivalTimeColumnProcess;

    @FXML
    private TextField processNumberField;
    @FXML
    private TextField cpuTimeField;
    @FXML
    private TextField priorityField;
    @FXML
    private TextField timeQuantumField;

    @FXML
    private TableView<ExecutionOrder> executionOrderTable;
    @FXML
    private TableColumn<ExecutionOrder, String> executionOrderColumn;
    @FXML
    private TableColumn<ExecutionOrder, String> executionProcessColumn;
    @FXML
    private TableColumn<ExecutionOrder, Number> arrivalTimeColumn;
    @FXML
    private TableColumn<ExecutionOrder, Number> turnaroundTimeColumn;
    @FXML
    private TableColumn<ExecutionOrder, Number> waitingTimeColumn;

    // Observable list to hold processes
    public final ObservableList<Process> processList = FXCollections.observableArrayList();
    private final ObservableList<ExecutionOrder> executionOrderList = FXCollections.observableArrayList();

    // Observable list to hold processes

    @FXML

    public void initialize() {

        // Initialize process table columns
        arrivalTimeColumnProcess.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        processNumberColumn.setCellValueFactory(new PropertyValueFactory<>("processNumber"));
        cpuTimeColumn.setCellValueFactory(new PropertyValueFactory<>("cpuTime"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        processTable.setItems(processList);

        processTable.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double tableWidth = newWidth.doubleValue();

            arrivalTimeColumnProcess.setPrefWidth(tableWidth * 0.2);
            processNumberColumn.setPrefWidth(tableWidth * 0.2);
            cpuTimeColumn.setPrefWidth(tableWidth * 0.2);
            priorityColumn.setPrefWidth(tableWidth * 0.2);
            statusColumn.setPrefWidth(tableWidth * 0.2);
        });

        setTableColumnProperties(arrivalTimeColumnProcess, processNumberColumn, cpuTimeColumn, priorityColumn,
                statusColumn);

        // Initialize execution order table columns
        executionOrderColumn.setCellValueFactory(new PropertyValueFactory<>("completionTime"));
        executionProcessColumn.setCellValueFactory(new PropertyValueFactory<>("processNumber"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        turnaroundTimeColumn.setCellValueFactory(new PropertyValueFactory<>("turnaroundTime"));
        waitingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));

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
        int cpuTime = Integer.parseInt(cpuTimeField.getText());
        int priority = Integer.parseInt(priorityField.getText());

        int arrivalTime = processList.size();

        Process newProcess = new Process(arrivalTime, cpuTime, priority, arrivalTime);

        processList.add(newProcess);

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
        if (processList.size() > 0) {
            for (Process process : processList) {
                process.setStatus("Ready");
            }
        }
        FCFS.runFCFS(processList, executionOrderList);
        if (executionOrderList.size() != 0)
            executionOrderList.clear();
        processTable.refresh();
        executionOrderTable.refresh();
    }

    @FXML
    public void handleRunSJFButtonAction(ActionEvent actionEvent) {
        if (processList.size() > 0) {
            for (Process process : processList) {
                process.setStatus("Ready");
            }
        }

        ObservableList<Process> copy = FXCollections.observableArrayList(processList);

        SJF.shortestJobFirst(copy, executionOrderList);
        if (executionOrderList.size() != 0)
            executionOrderList.clear();

    }

    @FXML
    public void handleRunRRButtonAction(ActionEvent actionEvent) {
        if (processList.size() > 0) {
            for (Process process : processList) {
                process.setStatus("Ready");
            }
        }
        RR.runRoundRobin(processList, executionOrderList, Integer.parseInt(timeQuantumField.getText()));
        if (executionOrderList.size() != 0)
            executionOrderList.clear();
    }

    @FXML
    public void handleRunPriorityButtonAction(ActionEvent actionEvent) {
        if (processList.size() > 0) {
            for (Process process : processList) {
                process.setStatus("Ready");
            }
        }
        Priority.runPriority(processList, executionOrderList);
        if (executionOrderList.size() != 0)
            executionOrderList.clear();
    }
}
