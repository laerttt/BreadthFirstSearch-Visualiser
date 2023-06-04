package com.example.bfsalgo;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import java.util.*;
class Graph {
    private int V;
    private LinkedList<Integer> adj[];
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    //adding edges
    void addEdges(int v) {
        if(!(v%10==0)) {
            adj[v].add(v - 1);
            for (Node node : Paint.grid.getChildren()) {
                if(((Cell)node).index == v){
                    System.out.println("setText");
                    ((Cell) node).label.setText("—  "+ ((Cell) node).label.getText());
                    break;
                }
            }
        }
        if(v>=10) {
            adj[v].add(v - 10);
            for (Node node : Paint.grid.getChildren()) {
                if(((Cell)node).index == v){
                    ((Cell) node).label.setText("|\n"+((Cell) node).label.getText());
                    break;
                }
            }
        }
        if(!(v%10==9)){
            adj[v].add(v+1);
            for (Node node : Paint.grid.getChildren()) {
                if(((Cell)node).index == v){
                    ((Cell) node).label.setText(((Cell) node).label.getText()+"  —");
                    break;
                }
            }
        }
        if(v<=89){
            adj[v].add(v+10);
            for (Node node : Paint.grid.getChildren()) {
                if(((Cell)node).index == v){
                    ((Cell) node).label.setText(((Cell) node).label.getText()+"\n|");
                    break;
                }
            }
        }
    }

    void BFS(int s,GridPane grid)  {
        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue
                = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);
        boolean finish = false;
        while (queue.size() != 0 && !finish) {
            s = queue.poll();
            for (Node node : grid.getChildren()) {
                if(s == ((Cell)node).index && !((Cell)node).start){
                    if (((Cell)node).finish)
                        finish = true;
                    Cell cell = ((Cell)node);
                    cell.ShowNumber();
                    break;
                }
            }
//            System.out.print(s + " ");
            Iterator<Integer> i = adj[s].listIterator();
//            System.out.println(i.hasNext());
            while (i.hasNext()) {
                int n = i.next();
                for (Node node : grid.getChildren()) {
                    if(n==((Cell)node).index) {
                        if (!((Cell) node).blank)
                            visited[n]=true;
                    }
                }
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                    for (Node node : grid.getChildren()) {
                        if(n == ((Cell)node).index){
                            Cell cell = ((Cell)node);
//                            System.out.println("akakakakak");
                            cell.addedToQ();
                            break;
                        }
                    }
                }
            }
        }
    }
}