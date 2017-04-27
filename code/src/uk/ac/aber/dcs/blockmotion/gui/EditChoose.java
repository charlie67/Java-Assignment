package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uk.ac.aber.dcs.blockmotion.model.IFootage;

/**
 * @author Charlie Robinson
 * @version 27.4.17
 */
public class EditChoose {

    public void display(IFootage footage, AddNewFrame addNewFrame, EditCurrentFrame editCurrentFrame){
        Platform.runLater (() -> {
            Stage window = new Stage();

            window.setTitle("Choose Edit Option");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(8);
            grid.setHgap(10);

            //add new frame button
            Button addFrame = new Button("Add New Frame");
            addFrame.setOnAction(event -> {
                addNewFrame.display(footage);
                window.close();
//                transformationsDone = true;
                //todo resolve this so that it gets updated in the addNewFrame thing and editFrame thing
            });
            GridPane.setConstraints(addFrame, 0,0);

            //edit existing frame button
            Button editFrame = new Button("Edit Existing Frame");
            editFrame.setOnAction(event -> {
                editCurrentFrame.display(footage);
                window.close();
            });
            GridPane.setConstraints(editFrame,0,1);

            grid.getChildren().addAll(editFrame,addFrame);

            Scene scene = new Scene(grid);

            window.setScene(scene);
            window.showAndWait();
        });
    }
}
