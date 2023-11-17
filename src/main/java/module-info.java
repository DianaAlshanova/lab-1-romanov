module com.example.lab1romanov {
    requires javafx.controls;
    requires javafx.fxml;
    requires exp4j;

    opens com.example.lab1romanov to javafx.fxml;
    exports com.example.lab1romanov;
    exports com.example.lab1romanov.presentation;
    opens com.example.lab1romanov.presentation to javafx.fxml;
}