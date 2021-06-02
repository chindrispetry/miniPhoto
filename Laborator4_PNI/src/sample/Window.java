package sample;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class Window {
    private BorderPane panou;
    public Window(){
        panou = new BorderPane();
    }

    public BorderPane getPanou() throws FileNotFoundException {
        TopMeniu top = new TopMeniu();
        LeftMeniu left = new LeftMeniu();
        RightMeniu right = new RightMeniu();
        left.designButtons();
        top.designButton();
        right.makePaletColor();

        HBox orizontalMenu = top.getTopMeniu();
        VBox leftMenu = left.getLeftMeniu();
        VBox rightMenu = new VBox(right.getPane());
        MiddleCanvas canvas = new MiddleCanvas();
        canvas.setDimension(500,400);
        top.addEventButton(canvas.getCanvas());
        left.addEventButton(canvas.getCanvas());
        StackPane holder = new StackPane();
        holder.getChildren().add(canvas.getCanvas());
        holder.setStyle("-fx-background-color: #ddd");

        HBox SecondSection = new HBox(leftMenu,holder,rightMenu);


        panou.setTop(orizontalMenu);
        panou.setLeft(SecondSection);
        panou.setPadding(new Insets(10));
        panou.setStyle("-fx-background-color: #7d7d7d");
        return panou;
    }
}
