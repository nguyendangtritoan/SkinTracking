package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.print.DocFlavor;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Controller {

    @FXML
    private Button UploadButton;

    @FXML
    private ImageView ImageView;

    @FXML
    private ImageView ImageView_Output;

    @FXML
    private Button Method2Button;

    @FXML
    private Button Method1Button;

    @FXML
    private Button Method3Button;

    private static String filenameIn;
    private static String filenameOut;

    private final FileChooser fileChooser = new FileChooser();
    @FXML
    void initialize() {
        UploadButton.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(((Node)event.getTarget()).getScene().getWindow());
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                System.out.println("This is image url 1: "+file.toURI().toString());
                ImageView.setImage(image);
                filenameIn = file.getAbsolutePath();
            }
        });
        Method1Button.setOnAction(event -> {
            try {
                filenameOut = filenameIn.split("\\.")[0] + "_out.png";
                explicitYes();

            File file1 = new File(filenameOut);
            Image image1 = new Image(file1.toURI().toString());
            ImageView_Output.setImage(image1);
            } catch (NullPointerException ef){
                System.out.println("There is no input file");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private static void explicitYes() throws Exception {
        int[][][] RGBArray = Image2Array.RGBArray(filenameIn);
        System.out.println("Opening image file " + filenameIn + ": Dimensions: " + RGBArray.length
                + "px x " + RGBArray[0].length + "px...");

        int[][] skinMask = Explicit.ExplicitYES(RGBArray);
        Array2Image.RGBImage(RGBArray, skinMask, filenameOut, "png");

        System.out.println("Wrote image to file " + filenameOut + "...");
    }
}
