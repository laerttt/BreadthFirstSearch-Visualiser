package com.example.bfsalgo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Paint extends Application {
    public static boolean temp = false;
    public static boolean wall = false;
    public static boolean del = false;
    public static boolean canAddStart = false;
    public static boolean canAddFinish = false;
    public static GridPane grid = new GridPane();
    public static int index =0;
    boolean edgesShown = false;

    @Override
    public void start(Stage stage)  {
        //fills grid with cells
        for(int x=0; x<10;x++){
            for(int y=0; y<10;y++){
                grid.add(new Cell(index),y,x);
                index++;
            }
        }
        //graph initialization
        Graph graph = new Graph(100);
        for(int i=0; i<100; i++){
            System.out.println("a");
            graph.addEdges(i);
        }
        //pane
        VBox pane = new VBox();
        pane.setPadding(new Insets(5));
        //for buttons
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5,5,0,5));
        hbox.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(grid,hbox);

        //buttons
        Button addStart = new Button("Add Start");
        addStart.setOnAction(e->{
            wall = false;
            del = false;
            for(Node cell : grid.getChildren()){
                if(((Cell)cell).start){
                    cell.setStyle("-fx-background-color: white");
                    cell.setStyle("-fx-border-color: black");
                    ((Cell) cell).start = false;
                    break;
                }
            }
            canAddStart = true;
        });
//        Button addFinish = new Button("Add Finish");
//        addFinish.setOnAction(e->{
//            temp = canAddStart;
//            canAddStart = false;
//            wall = false;
//            del = false;
//            if(!canAddFinish){
//                for(Node cell : grid.getChildren()){
//                    if(((Cell) cell).finish){
//                        cell.setStyle("-fx-background-color: white");
//                        cell.setStyle("-fx-border-color: black");
//                        ((Cell) cell).finish = false;
//                        break;
//                    }
//                }
//            }
//            canAddFinish = true;
//        });
        Button reset = new Button("Reset");
        reset.setOnAction(e->{
            for(Node cell : grid.getChildren()){
                //make every cell balnk
                ((Cell)cell).setColor(false,true);
            }
        });
        Button addWall = new Button("Create Wall");
        addWall.setOnAction(e->{wall=true; del=false;});
        Button showEdges = new Button("Show Edges");
        showEdges.setOnAction(e->{
            if(edgesShown){
                Warning.warning("Edges are already shown!");
            }
            else{
                for(Node node: grid.getChildren()){
                    ((Cell) node).showEdges();
                }
                this.edgesShown = true;
            }
        });
        addWall.setOnAction(e->{wall=true; del=false;});
        Button delWall = new Button("Delete Wall");
        delWall.setOnAction(e->{del=true; wall=false;});
        Button bfs = new Button("BFS");
        bfs.setOnAction(e-> {
            Integer start=null;
            for(Node node: grid.getChildren()){
                if(((Cell)node).start) {
                    start = ((Cell) node).index;
                }
            }
            try {
                graph.BFS(start, grid);
            } catch (Exception ex) {
                Stage x = Warning.warning("Please select a starting point!");
                x.show();;
            }
        });
        Button randomizer = new Button("Randomize");
        randomizer.setOnAction(e->{
            for(Node cell : grid.getChildren()){
                //make every cell balnk
                ((Cell)cell).setColor(false,true);
            }
            for(Node cell : grid.getChildren()){
                if(Math.random()>Math.random())
                //make every cell balnk
                    ((Cell)cell).setColor(true,false);
            }
        });

        hbox.setSpacing(10);
        hbox.getChildren().addAll(addWall, delWall, addStart/*, addFinish*/, bfs, showEdges, reset,randomizer);

        //scene
        Scene scene= new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}