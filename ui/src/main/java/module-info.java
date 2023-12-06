module colab {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens colab to javafx.fxml;
    exports colab;

    opens model to javafx.fxml;
    exports model;
}
