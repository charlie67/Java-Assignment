package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Charlie Robinson
 * @version 17.4.17
 */
public class LoadWindow {
    private static String fn;
    private static boolean shouldLoad;

    public static boolean display(){
        Platform.runLater(() -> {
//        FileChooser fileChooser = new FileChooser();
            Stage window = new Stage();

            window.setTitle("Load");

            //make the load window the only window that can be clicked on
            window.initModality(Modality.APPLICATION_MODAL);

            Label instructions = new Label();
            instructions.setText("Enter the file name you want to load");

            TextField fileName = new TextField();


            Button cancel = new Button("Cancel");
            cancel.setOnAction(e -> {
                shouldLoad = false;
                window.close();
            });

            Button ok = new Button("OK");
            ok.setOnAction(e -> {
                shouldLoad = true;
                fn = fileName.getText();
                window.close();
            });

            HBox layout = new HBox(10);
            layout.getChildren().addAll(instructions,cancel, fileName, ok);

            Scene scene = new Scene(layout);

            window.setScene(scene);
            window.showAndWait();
        });
        return shouldLoad;
    }

    public static String getFn() {
        return fn;
    }

    public static boolean isShouldLoad() {
        return shouldLoad;
    }
}
