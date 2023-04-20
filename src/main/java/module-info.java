module com.example.snakeladder3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeladder3 to javafx.fxml;
    exports com.example.snakeladder3;
}