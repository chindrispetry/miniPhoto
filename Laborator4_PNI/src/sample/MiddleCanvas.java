package sample;

import javafx.scene.canvas.Canvas;

public class MiddleCanvas {
    private Canvas canvas;
    public MiddleCanvas(){
        canvas = new Canvas();
    }
    public void setDimension(double width,double height){
        canvas.setWidth(width);
        canvas.setHeight(height);
    }
    public Canvas getCanvas(){
        return canvas;
    }
}
