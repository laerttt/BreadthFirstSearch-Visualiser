package com.example.bfsalgo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Warning {
    public static Stage warning(String message) {
        //stage
        Stage stage = new Stage();

        //Pane
        BorderPane bPane = new BorderPane();

        //Scene
        Scene scene = new Scene(bPane);

        //messageLabel
        Text info = new Text(message+"\n");
        info.setStyle("-fx-font-size: 16px;");
        info.setTextAlignment(TextAlignment.CENTER);
        //buttons
        Button close = new Button("Close");
        close.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        //arrangements
        bPane.setPadding(new Insets(5));
        bPane.setStyle("-fx-font-size: 15px;");
        bPane.setCenter(info);
        BorderPane.setAlignment(info, Pos.CENTER);
        bPane.setBottom(close);
        BorderPane.setAlignment(close, Pos.BOTTOM_RIGHT);

        //actions
        close.setOnAction(e -> stage.close());

        stage.setTitle("Warning!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        return stage;
    }
}
