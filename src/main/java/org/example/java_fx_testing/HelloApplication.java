package org.example.java_fx_testing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();

        // Load the FXML file for the UI
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Set the root of the ScrollPane as the content from the FXML
        scrollPane.setContent(fxmlLoader.load());

        // Optionally, you can configure the scroll pane properties (e.g., set fit to width)
        scrollPane.setFitToWidth(true);

        // Create the scene and set the scrollPane as its root
        Scene scene = new Scene(scrollPane, 320, 240);

        // Set the title and scene for the stage
        stage.setTitle("CPU S");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
