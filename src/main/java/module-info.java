module org.example.java_fx_testing {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.java_fx_testing to javafx.fxml;
    exports org.example.java_fx_testing;

    opens algos to javafx.base;
    exports algos;


}