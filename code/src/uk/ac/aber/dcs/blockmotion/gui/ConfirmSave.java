package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * @author Charlie Robinson
 * @version 22.4.17
 */
public class ConfirmSave {

    public void display(String fileName){
        Platform.runLater(() -> {
            Stage window = new Stage();

            window.setTitle("Blockmation " + fileName);
        });
    }
}
