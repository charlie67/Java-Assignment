package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uk.ac.aber.dcs.blockmotion.model.IFootage;

import java.io.IOException;

/**
 * @author Charlie Robinson
 * @version 22.4.17
 */
public class ConfirmSave {
    //variables used in lambdas require the variable to be declared private
    private boolean close = false;

    public void display(String fileName, IFootage footage, Stage window){
        Platform.runLater(() -> {

            //make the load window the only window that can be clicked on
//            window.initModality(Modality.APPLICATION_MODAL);

            GridPane grid = new GridPane();
            grid.setPadding(new javafx.geometry.Insets(10));
            grid.setVgap(8);
            grid.setHgap(10);

            Button save = new Button("Save");
            Button no = new Button("Don't Save");
            Button cancel = new Button("Cancel");

            Label label = new Label("File has been modified. Do you want to save?");

            //cancel button
            cancel.setOnAction(event -> {
                close = false;
                window.close();
            });

            //save button
            save.setOnAction(event -> {
                try {
                    footage.save(fileName);
                }catch (IOException e){

                }
                close = true;
                //closing the window needs to be handled in the Animator Class
                window.fireEvent(
                        new WindowEvent(
                                window,
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                );
                //this is needed because Stage.setOnCloseRequest is only called when an external close request is called
                //this is when the exit button is pressed the window.fireEvent simulates an external close request so that
                //the .setOnCloseRequest in Animator.java work
                //the above code came from https://stackoverflow.com/questions/29710492/how-can-i-fire-internal-close-request
                //on 22.4.17
            });

            //don't save
            no.setOnAction(event -> {
                close = true;
                window.fireEvent(
                        new WindowEvent(
                                window,
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                );
                //the above code came from https://stackoverflow.com/questions/29710492/how-can-i-fire-internal-close-request
                //on 22.4.17
            });


            GridPane.setConstraints(save, 1,1);
            GridPane.setConstraints(no, 2,1);
            GridPane.setConstraints(cancel, 3,1);

            grid.add(label,0,0,5,1);
            GridPane.setHalignment(label, HPos.CENTER);

            grid.getChildren().addAll(save,no, cancel);

            Scene scene = new Scene(grid);

            window.setTitle("Blockmation " + fileName);
            window.setScene(scene);
            window.showAndWait();
        });
    }

    public boolean isClose() {
        return close;
    }
}
