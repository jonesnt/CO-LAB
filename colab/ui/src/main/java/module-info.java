module colab {
    requires javafx.controls;
    requires javafx.fxml;

    opens colab to javafx.fxml;
    exports colab;

    opens model to javafx.fxml;
    exports model;
}
