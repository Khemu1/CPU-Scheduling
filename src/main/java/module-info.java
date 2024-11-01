module org.example.java_fx_testing {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.java_fx_testing to javafx.fxml;
    exports org.example.java_fx_testing;

    opens algos to javafx.base;
    exports algos;


}