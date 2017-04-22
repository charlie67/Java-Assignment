package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This was used to set the file name but it's no longer used because I couldn't figure out how to properly
 * get the file name that was set when the ok button was pressed.
 * @author Charlie Robinson
 * @version 17.4.17
 */
public class SetFileName {
    private static String fn;
    private static boolean shouldLoad;

    public static void display(){
        Platform.runLater(() -> {
            Stage window = new Stage();

            window.setTitle("Load");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(8);
            grid.setHgap(10);

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

            //sets where the items will be in the grid
            GridPane.setConstraints(instructions, 0,0);
            GridPane.setConstraints(fileName, 0, 1);
            GridPane.setConstraints(cancel,0,2);
            GridPane.setConstraints(ok,1,2);

            grid.getChildren().addAll(instructions,cancel, fileName, ok);

            Scene scene = new Scene(grid);

            window.setScene(scene);
            window.showAndWait();
        });
    }

    public static String getFn() {
        return fn;
    }

    public static boolean isShouldLoad() {
        return shouldLoad;
    }
}