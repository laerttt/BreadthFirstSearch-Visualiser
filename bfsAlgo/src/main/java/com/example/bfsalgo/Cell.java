package com.example.bfsalgo;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Cell extends StackPane {
    Rectangle rectangle = new Rectangle(70, 70);
    FadeTransition ft = new FadeTransition(Duration.millis(1000), rectangle);
    public boolean start = false;
    public boolean wall = false;
    public boolean finish = false;
    public boolean blank = true;
    static int countadj = 0;
    static int count = 1;
    public boolean showNumber = false;
    public String shownNum ="";
    Integer index = null;
    Label label;

    // Token used for this cell
    public Cell(int index) {
        //basic pane
        label = new Label(" ");
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        rectangle.setOpacity(0);

        //properties
        this.getChildren().add(rectangle);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        this.index = index;
        setStyle("-fx-border-color: black");
        this.setMinSize(70,70);
        this.setMaxSize(70,70);
        this.setOnDragDetected(e-> {
            this.startFullDrag();
        });
        this.setOnMouseDragOver(e->setColor(Paint.wall, Paint.del));
        this.setOnMouseClicked(e->setColor(Paint.wall, Paint.del, Paint.canAddStart, Paint.canAddFinish));
//        this.setOnMouseClicked(e->{
//            this.rectangle.setFill(Color.GRAY);
////        this.setStyle("-fx-background-color: rgb(169,169,169)");
//            ft.play();
//        });
    }

    //painting meth
    public void setColor(boolean wall, boolean delete) {
        if(wall) {
            setStyle("-fx-background-color: black");
            this.wall = true;
            this.start = false;
            this.finish = false;
            this.blank = false;
        }
        else if(delete) {
            setStyle("-fx-background-color: white");
            setStyle("-fx-border-color: black");
            this.wall = false;
            this.start = false;
            this.finish = false;
            this.blank = true;
            this.rectangle.setOpacity(0);
        }
    }
    public void setColor(boolean wall, boolean delete, boolean canAddStart, boolean canAddFinish) {
        if(wall) {
            setStyle("-fx-background-color: rgb(0,0,0)");
            this.wall = true;
            this.start = false;
            this.finish = false;
            this.blank = false;
        }
        else if(delete) {
            setStyle("-fx-background-color: white");
            setStyle("-fx-border-color: black");
            this.wall = false;
            this.start = false;
            this.finish = false;
            this.blank = true;
            this.rectangle.setOpacity(0);
        }
        else if(canAddStart){
            setStyle("-fx-background-color: green");
            Paint.canAddStart = false;
            this.wall = false;
            this.finish = false;
            this.start = true;
            this.blank = false;
        }
        else if(canAddFinish){
            setStyle("-fx-background-color: blue");
            Paint.canAddFinish = false;
            Paint.canAddStart = Paint.temp;
            this.wall = false;
            this.start = false;
            this.finish = true;
            this.blank = false;
        }
    }
    public void addedToQ(){
        this.rectangle.setFill(Color.BISQUE);
        countadj++;
        ft.setDelay(Duration.millis(countadj*100));
//        this.setStyle("-fx-background-color: rgb(184,134,11)");
        ft.play();
    }

    //doesn't show num shows color
    public void ShowNumber() {
        showNumber = true;
        this.rectangle.setFill(Color.BLUE);
        count++;
        ft.setDelay(Duration.millis(count*100));
//        this.setStyle("-fx-background-color: rgb(169,169,169)");
        ft.play();
    }

    //show edges
    public void showEdges(){
        this.getChildren().add(label);
    }
}

