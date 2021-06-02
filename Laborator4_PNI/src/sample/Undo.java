package sample;

import javafx.scene.image.Image;

import java.util.Deque;
import java.util.LinkedList;

public class Undo {
    private Deque<Image> history = new LinkedList<>();
    private static Undo undo = new Undo();
    private Undo(){

    }
    public static Undo getUndo(){return undo;}

    public void addIMG(Image img){
        history.addFirst(img);
    }
    public Image getIMG(){
        return history.getLast();
    }
    public void clearHistory(){
        history = new LinkedList<>();
    }
    public int getHistoryLength(){
        return history.size();
    }
}
