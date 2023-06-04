module com.example.bfsalgo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bfsalgo to javafx.fxml;
    exports com.example.bfsalgo;
}