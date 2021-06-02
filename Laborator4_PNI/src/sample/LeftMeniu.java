package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class LeftMeniu {
    private Button btnLinie;
    private Button btnBrush;
    private Button btnZoom;
    private Button btnHistogram;
    private Button btnButoi;
    private Button btnPerna;
    private Button btnAuriu;
    private Button btnGlaciar;
    public static Color colorDrawing = Color.RED;
    public LeftMeniu(){
        btnLinie = new Button();
        btnBrush = new Button();
        btnZoom = new Button();
        btnHistogram = new Button();
        btnButoi = new Button();
        btnPerna = new Button();
        btnAuriu = new Button();
        btnGlaciar = new Button();
    }
    public void designButtons() throws FileNotFoundException {
        Image img = new Image(new FileInputStream("src/sample/icon/brush.jpg"));
        ImageView view = new ImageView(img);
        view.setFitWidth(30);
        view.setFitHeight(30);
        btnBrush.setGraphic(view);

        Image img1 = new Image(new FileInputStream("src/sample/icon/line.png"));
        ImageView view1 = new ImageView(img1);
        view1.setFitWidth(30);
        view1.setFitHeight(30);
        btnLinie.setGraphic(view1);

        Image img2 = new Image(new FileInputStream("src/sample/icon/zoom.png"));
        ImageView view2 = new ImageView(img2);
        view2.setFitWidth(30);
        view2.setFitHeight(30);
        btnZoom.setGraphic(view2);

        Image img3 = new Image(new FileInputStream("src/sample/icon/histograma.jpg"));
        ImageView view3 = new ImageView(img3);
        view3.setFitWidth(30);
        view3.setFitHeight(30);
        btnHistogram.setGraphic(view3);


        Image img5 = new Image(new FileInputStream("src/sample/icon/butoi.png"));
        ImageView view5 = new ImageView(img5);
        view5.setFitWidth(30);
        view5.setFitHeight(30);
        btnButoi.setGraphic(view5);

        Image img6 = new Image(new FileInputStream("src/sample/icon/perna.png"));
        ImageView view6 = new ImageView(img6);
        view6.setFitWidth(30);
        view6.setFitHeight(30);
        btnPerna.setGraphic(view6);

        Image img7 = new Image(new FileInputStream("src/sample/icon/auriu.jpg"));
        ImageView view7 = new ImageView(img7);
        view7.setFitWidth(30);
        view7.setFitHeight(30);
        btnAuriu.setGraphic(view7);

        Image img8 = new Image(new FileInputStream("src/sample/icon/fulg.jpg"));
        ImageView view8 = new ImageView(img8);
        view8.setFitWidth(30);
        view8.setFitHeight(30);
        btnGlaciar.setGraphic(view8);
    }
    public void addEventButton(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        EventHandler<MouseEvent> brushPressed = mouseEvent -> {
            gc.beginPath();
            gc.setStroke(colorDrawing);
            gc.stroke();
        };
        EventHandler<MouseEvent> brushDragged = mouseEvent -> {
            gc.lineTo(mouseEvent.getSceneX() -70,mouseEvent.getSceneY() -70);
            gc.stroke();
        };
        EventHandler<MouseEvent> brushReleased = mouseEvent -> {
            gc.stroke();
            gc.closePath();
            Undo.getUndo().addIMG(canvas.snapshot(new SnapshotParameters(),null));
        };
        EventHandler<MouseEvent> linePressed = mouseEvent -> {
            gc.beginPath();
            gc.setStroke(colorDrawing);
            gc.lineTo(mouseEvent.getSceneX() - 70,mouseEvent.getSceneY() - 70);
        };
        EventHandler<MouseEvent> lineReleased = mouseEvent -> {
            gc.lineTo(mouseEvent.getSceneX() - 70,mouseEvent.getSceneY() - 70);
            gc.stroke();
            gc.closePath();
            Undo.getUndo().addIMG(canvas.snapshot(new SnapshotParameters(),null));
        };
        EventHandler<MouseEvent> zoomMoved = mouseEvent -> {
            addMouseScrolling(canvas,mouseEvent.getSceneX(),mouseEvent.getSceneY());
        };
        btnBrush.setOnAction((e)->{
            canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED,linePressed);
            canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED,lineReleased);

            canvas.removeEventHandler(MouseEvent.MOUSE_MOVED,zoomMoved);

            canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,brushPressed);
            canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,brushDragged);
            canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,brushReleased);
        });
        btnLinie.setOnAction((e)->{
            canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED,brushPressed);
            canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED,brushDragged);
            canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED,brushReleased);

            canvas.removeEventHandler(MouseEvent.MOUSE_MOVED,zoomMoved);

            canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,linePressed);
            canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,lineReleased);
        });
        btnZoom.setOnAction((e)->{
            canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED,brushPressed);
            canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED,brushDragged);
            canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED,brushReleased);

            canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED,linePressed);
            canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED,lineReleased);

            canvas.addEventHandler(MouseEvent.MOUSE_MOVED,zoomMoved);
        });
        btnHistogram.setOnAction((e)->{
            Image img = canvas.snapshot(new SnapshotParameters(),null);
            PixelReader pixelReader = img.getPixelReader();
            int width = (int)img.getWidth();
            int height = (int)img.getHeight();
            WritableImage newIMG = new WritableImage(width,height);
            PixelWriter pixelWriter = newIMG.getPixelWriter();
            for(int i = 0 ;i < width ;i++){
                for (int j = 0;j<height;j++){
                    Color color = pixelReader.getColor(i,j);
                    color = makeGrey(color);
                    pixelWriter.setColor(i,j,color);
                }
            }
            Undo.getUndo().addIMG(newIMG);
            canvas.getGraphicsContext2D().drawImage(newIMG,0,0);
        });
        btnButoi.setOnAction((e)->{
            makeDistorsion(canvas,-0.000581,0);
        });
        btnPerna.setOnAction((e)->{
            makeDistorsion(canvas,-0.000794,0);

        });
        btnAuriu.setOnAction((e)->{
            Image img = canvas.snapshot(new SnapshotParameters(),null);
            PixelReader pixelReader = img.getPixelReader();
            int width = (int)img.getWidth();
            int height = (int)img.getHeight();
            WritableImage newIMG = new WritableImage(width,height);
            PixelWriter pixelWriter = newIMG.getPixelWriter();
            Limite limite = new Limite(0,0,0,0,55,190,55,0);
            Limite limite1 = new Limite(55,190,55,0,155,255,190,50);
            Limite limite2 = new Limite(155,255,190,50,255,255,255,255);
            int[] red = new int[256];
            int[] blue = new int[256];
            int[] green = new int[256];
            addGradient(limite,red,blue,green);
            addGradient(limite1,red,blue,green);
            addGradient(limite2,red,blue,green);
            for(int i = 0 ;i < width ;i++){
                for (int j = 0;j<height;j++){
                    Color color = pixelReader.getColor(i,j);
                    int[] arr = makeBlue(color);
                    pixelWriter.setColor(i,j,Color.rgb(red[arr[0]],blue[arr[1]],green[arr[2]]));
                }
            }
            Undo.getUndo().addIMG(newIMG);
            canvas.getGraphicsContext2D().drawImage(newIMG,0,0);
        });
        btnGlaciar.setOnAction((e)->{
            Image img = canvas.snapshot(new SnapshotParameters(),null);
            PixelReader pixelReader = img.getPixelReader();
            int width = (int)img.getWidth();
            int height = (int)img.getHeight();
            WritableImage newIMG = new WritableImage(width,height);
            PixelWriter pixelWriter = newIMG.getPixelWriter();
            Limite limite = new Limite(0,0,0,0,55,0,65,205);
            Limite limite1 = new Limite(55,0,65,205,155,65,205,255);
            Limite limite2 = new Limite(155,65,205,255,255,255,255,255);
            int[] red = new int[256];
            int[] blue = new int[256];
            int[] green = new int[256];
            addGradient(limite,red,blue,green);
            addGradient(limite1,red,blue,green);
            addGradient(limite2,red,blue,green);
            for(int i = 0 ;i < width ;i++){
                for (int j = 0;j<height;j++){
                    Color color = pixelReader.getColor(i,j);
                    int[] arr = makeBlue(color);
                    pixelWriter.setColor(i,j,Color.rgb(red[arr[0]],blue[arr[1]],green[arr[2]]));
                }
            }
            Undo.getUndo().addIMG(newIMG);
            canvas.getGraphicsContext2D().drawImage(newIMG,0,0);
        });
    }
    private void addGradient(Limite limite,int[] red,int[] blue,int[] green){
        int delta = 255 / (limite.getSup() - limite.getInf());
        for (int i = limite.getInf();i<limite.getSup();i++){
            int temp = (i - limite.getInf()) * delta;
            red[i] = gradient(limite.getInfR(),limite.getSupR(),temp);
            blue[i] = gradient(limite.getInfB(),limite.getSupB(),temp);
            green[i] = gradient(limite.getInfG(),limite.getSupG(),temp);
        }
    }

    private int gradient(int infB, int supB, int temp) {
        if(temp == 0)return infB;
        if(temp == 255) return infB;
        else return (infB*(255 - temp) + supB *temp)/256;
    }

    private int[] makeBlue(Color color) {
        int[] arr   = new int[3];
        arr[0] = (int)(color.getRed()*255);
        arr[1] = (int)(color.getBlue()*255);
        arr[2] = (int)(color.getGreen()*255);
        return arr;
    }

    public void makeDistorsion(Canvas canvas,double k1,double k2){
        Image img = canvas.snapshot(new SnapshotParameters(),null);
        ImageView view = new ImageView(img);
        view.setFitHeight(380);
        view.setFitWidth(470);
        PixelReader pixelReader = view.snapshot(new SnapshotParameters(),null).getPixelReader();
        int width = (int)img.getWidth();
        int height = (int)img.getHeight();
        WritableImage newIMG = new WritableImage(width + 1,height + 1);
        PixelWriter pixelWriter = newIMG.getPixelWriter();
        for(int i = -width/2;i <= 0  ;i++){
            for (int j = -height/2;j <= 0;j++){
                int px,py,cx = 0,cy = 0;
                double r;
                r = Math.sqrt(Math.abs((Math.abs(i)-cx)*(Math.abs(i)-cx)) +
                              Math.abs((Math.abs(j)-cy)*(Math.abs(j)-cy)));

                px = (int) ( i * (1 + k1 * r + k2 * r * r));
                py = (int) ( j * (1 + k1 * r + k2 * r * r));
                if(Math.abs(px) < width/2 && Math.abs(py) < height/2) {
                    pixelWriter.setColor(i + width/2, j + height/2,
                            pixelReader.getColor(px + width/2, py + height/2));
                    pixelWriter.setColor(-i + width/2, j + height/2,
                            pixelReader.getColor(-px + width/2, py + height/2));
                    pixelWriter.setColor(i + width/2, -j + height/2,
                            pixelReader.getColor(px + width/2, -py + height/2));
                    pixelWriter.setColor(-i + width/2, -j + height/2,
                            pixelReader.getColor(-px + width/2, -py + height/2));
                }
            }
        }
        Undo.getUndo().addIMG(newIMG);
        canvas.getGraphicsContext2D().drawImage(newIMG,14,10);
    }
    private Color makeGrey(Color color) {
        double r = color.getRed();
        double b = color.getBlue();
        double g = color.getGreen();
        double medie = (r + b + g) / 3;
        return Color.gray(medie);
    }

    public void addMouseScrolling(Canvas canvas,double x, double y) {
        canvas.setOnScroll((ScrollEvent event) -> {
            // Adjust the zoom factor as per your requirement
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0){
                zoomFactor = 2.0 - zoomFactor;
            }
            if(canvas.getScaleX() * zoomFactor  < 1 && canvas.getScaleY() * zoomFactor  < 1) {
                canvas.setScaleX(canvas.getScaleX() * zoomFactor);
                canvas.setScaleY(canvas.getScaleY() * zoomFactor);
            }
        });
    }
    public VBox getLeftMeniu(){
        VBox left = new VBox(btnBrush,btnLinie,btnZoom,btnHistogram,btnAuriu,btnGlaciar,btnButoi,btnPerna);
        left.setPadding(new Insets(10));
        return left;
    }
}
