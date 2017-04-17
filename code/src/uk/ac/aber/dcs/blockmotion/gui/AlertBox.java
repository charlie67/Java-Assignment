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
 * @author Charlie
 * @version 17.4.17
 */
public class AlertBox {

    public static void display(String title, String text){
        Platform.runLater(() -> {
            Stage window = new Stage();

            window.setTitle(title);

            //make the load window the only window that can be clicked on
            window.initModality(Modality.APPLICATION_MODAL);

            Label message = new Label();
            message.setText(text);

            Button ok = new Button();
            ok.setText("OK");
            ok.setOnAction(e -> window.close());

            HBox layout = new HBox(10);

            layout.getChildren().addAll(message, ok);

            Scene scene = new Scene(layout);

            window.setScene(scene);
            window.showAndWait();
        });
    }
}
