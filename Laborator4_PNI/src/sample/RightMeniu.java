package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.TreeMap;


public class RightMeniu {
    private GridPane paletColor;
    private TreeMap<String,Color> colours = new TreeMap<>();
    public RightMeniu(){
        paletColor = new GridPane();
    }
    public void makePaletColor(){
        Button[] buttons = new Button[16];
        for(int i = 0 ; i < 16 ;i++){
            buttons[i] = new Button();
            buttons[i].setPrefHeight(30);
            buttons[i].setPrefWidth(30);
            int finalI = i;
            buttons[i].setOnAction((e)->{
                String[] values = buttons[finalI].getStyle().split(":");
                LeftMeniu.colorDrawing = colours.get(values[1]);
            });
        }
        colours.put("black",Color.BLACK);
        colours.put("white",Color.WHITE);
        colours.put("yellow",Color.YELLOW);
        colours.put("purple",Color.PURPLE);
        colours.put("aqua",Color.AQUA);
        colours.put("blue",Color.BLUE);
        colours.put("green",Color.GREEN);
        colours.put("brown",Color.BROWN);
        colours.put("pink",Color.PINK);
        colours.put("grey",Color.GREY);
        colours.put("turquoise",Color.TURQUOISE);
        colours.put("chocolate",Color.CHOCOLATE);
        colours.put("snow",Color.SNOW);
        colours.put("tomato",Color.TOMATO);
        colours.put("papayawhip",Color.PAPAYAWHIP);

        buttons[0].setStyle("-fx-background-color:black");
        buttons[1].setStyle("-fx-background-color:white");
        buttons[2].setStyle("-fx-background-color:yellow");
        buttons[3].setStyle("-fx-background-color:purple");
        buttons[4].setStyle("-fx-background-color:aqua");
        buttons[5].setStyle("-fx-background-color:blue");
        buttons[6].setStyle("-fx-background-color:green");
        buttons[7].setStyle("-fx-background-color:orange");
        buttons[8].setStyle("-fx-background-color:brown");
        buttons[9].setStyle("-fx-background-color:pink");
        buttons[10].setStyle("-fx-background-color:grey");
        buttons[11].setStyle("-fx-background-color:turquoise");
        buttons[12].setStyle("-fx-background-color:chocolate");
        buttons[13].setStyle("-fx-background-color:snow");
        buttons[14].setStyle("-fx-background-color:tomato");
        buttons[15].setStyle("-fx-background-color:papayawhip");
        int  k = 0;
        for (int i = 0 ; i< 4 ;i++){
            for(int j = 0; j < 4 ;j++){
                paletColor.add(buttons[k++],i,j);
            }
        }
        paletColor.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;");
        paletColor.setPadding(new Insets(20));
    }
    public GridPane getPane(){return paletColor;}
}
