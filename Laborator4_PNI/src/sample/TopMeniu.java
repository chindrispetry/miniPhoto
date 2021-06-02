package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class TopMeniu {
    private Button btnNew;
    private Button btnOpen;
    private Button btnSave;
    private Button btnUndo;
    public TopMeniu(){
        btnNew = new Button("New");
        btnOpen = new Button("Open");
        btnSave = new Button("Save");
        btnUndo = new Button("Undo");
    }
    public void designButton() throws FileNotFoundException {
        Image img = new Image(new FileInputStream("src/sample/icon/new.png"));
        ImageView view = new ImageView(img);
        view.setFitWidth(30);
        view.setFitHeight(30);
        btnNew.setGraphic(view);

        Image img1 = new Image(new FileInputStream("src/sample/icon/open.png"));
        ImageView view1 = new ImageView(img1);
        view1.setFitWidth(30);
        view1.setFitHeight(30);
        btnOpen.setGraphic(view1);

        Image img2 = new Image(new FileInputStream("src/sample/icon/save.png"));
        ImageView view2 = new ImageView(img2);
        view2.setFitWidth(30);
        view2.setFitHeight(30);
        btnSave.setGraphic(view2);

        Image img3 = new Image(new FileInputStream("src/sample/icon/undo.png"));
        ImageView view3 = new ImageView(img3);
        view3.setFitWidth(30);
        view3.setFitHeight(30);
        btnUndo.setGraphic(view3);
    }
    public void addEventButton(Canvas canvas){
        btnNew.setOnAction((e)->{
            Image img = generateImage(1,1,1,1);
            ImageView view = new ImageView(img);
            view.setFitHeight(380);
            view.setFitWidth(470);
            img = view.snapshot(new SnapshotParameters(),null);
            Undo.getUndo().clearHistory();
            Undo.getUndo().addIMG(img);
            canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
            canvas.getGraphicsContext2D().drawImage(img,14,10);
        });
        FileChooser file = new FileChooser();
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("File *.jpg","*.jpg"),
                new FileChooser.ExtensionFilter("File *.png","*.png"),
                new FileChooser.ExtensionFilter("File *.bmp","*.bmp"));
        btnOpen.setOnAction((e)->{
            try {
                File nFile = file.showOpenDialog(null);
                if(nFile != null) {
                    Image img = new Image(new FileInputStream(nFile));
                    ImageView view = new ImageView(img);
                    view.setFitHeight(380);
                    view.setFitWidth(470);
                    img = view.snapshot(new SnapshotParameters(), null);
                    Undo.getUndo().clearHistory();
                    Undo.getUndo().addIMG(img);
                    canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    canvas.getGraphicsContext2D().drawImage(img, 14, 10);
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        btnSave.setOnAction((e)->{
            Image img = canvas.snapshot(new SnapshotParameters(),null);
            File fout = file.showSaveDialog(null);
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(img,null);
            try {
                ImageIO.write(bufferedImage,"png",fout);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        btnUndo.setOnAction((e)->{
            canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
            canvas.getGraphicsContext2D().drawImage(Undo.getUndo().getIMG(), 14,10);
        });
    }
    private Image generateImage(double red, double green, double blue, double opacity) {
        WritableImage img = new WritableImage(1, 1);
        PixelWriter pw = img.getPixelWriter();

        Color color = Color.color(red, green, blue, opacity);
        pw.setColor(0, 0, color);
        return img ;
    }
    public HBox getTopMeniu(){
        HBox top = new HBox(btnNew,btnOpen,btnSave,btnUndo);
        top.setPadding(new Insets(10));
        return top;
    }
}
