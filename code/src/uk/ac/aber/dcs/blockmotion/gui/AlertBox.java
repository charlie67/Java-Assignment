package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Charlie Robinson
 * @version 20.4.17
 */
public class AlertBox {

    /**
     * Creates an alert window that pops up to the user
     * @param title The title of the window
     * @param text The text to display in the window
     */
    public static void display(String title, String text){
        Platform.runLater(() -> {
            Stage window = new Stage();

            window.setTitle(title);

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(8);
            grid.setHgap(10);

            //make the load window the only window that can be clicked on
            window.initModality(Modality.APPLICATION_MODAL);

            Label message = new Label();
            message.setText(text);

            Button ok = new Button();
            ok.setText("OK");
            ok.setOnAction(e -> window.close());

           //sets the items on the grid
            GridPane.setConstraints(message, 0,0);
            GridPane.setHalignment(message, HPos.CENTER);


            GridPane.setConstraints(ok,0,1);
            GridPane.setHalignment(ok, HPos.CENTER);


            grid.getChildren().addAll(message, ok);

            Scene scene = new Scene(grid);

            window.setScene(scene);
            window.showAndWait();
        });
    }
}
