package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import uk.ac.aber.dcs.blockmotion.model.Frame;
import uk.ac.aber.dcs.blockmotion.model.IFootage;

import java.util.Random;


/**
 * @author Charlie Robinson
 * @version 27.4.17
 */
public class AddNewFrame {
    private boolean transformationsDone = false;

    public void display(IFootage footage){
        Platform.runLater(() -> {
            Stage window = new Stage();

            window.setTitle("Add New Frame");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(8);
            grid.setHgap(10);

            Label numFrameLabel = new Label("Number of Frame to Add");
            Label colourSet =  new Label("Colour to set the Frame to");

            TextField numFrame = new TextField("1");

            ChoiceBox<String> charSet = new ChoiceBox<>();

            Button set = new Button("Set");
            Button cancel = new Button("Cancel");

            charSet.getItems().addAll("b - Blue", "l - Yellow", "r - Black", "h - Red", "p - Purple", "g - Green",
                    "q - Random Colour");
            charSet.setValue("b - Blue");

            //set button
            set.setOnAction(event -> {

                int framesToAdd;

                try {
                    framesToAdd = Math.abs(Integer.parseInt(numFrame.getText()));
                    //take the absolute value in case a negative value is entered

                    transformationsDone = true;

                    for (int z = 0; z<framesToAdd; z++) {

                        Frame frame = new Frame(footage.getNumRows());

                        for (int i = 0; i < footage.getNumRows(); i++) {
                            for (int j = 0; j < footage.getNumRows(); j++) {

                                char replace = charSet.getValue().charAt(0);

                                if (replace == 'q') {

                                    Random random = new Random();
                                    int value = random.nextInt(7);
                                    /*will generate a random value between 0 and 6
                                    got this from https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java
                                    on the 27.4.17
                                     */

                                    switch (value) {
                                        case 0:
                                            replace = 'b';
                                            break;
                                        case 1:
                                            replace = 'l';
                                            break;
                                        case 2:
                                            replace = 'r';
                                            break;
                                        case 3:
                                            replace = 'h';
                                            break;
                                        case 4:
                                            replace = 'p';
                                            break;
                                        case 5:
                                            replace = 'g';
                                            break;
                                        case 6:
                                            replace = 'm';
                                            //should trigger default choice on Animation.runAnimation
                                            //so that the square is white
                                            break;
                                        default:
                                            //in case something happens and the bound is wrong
                                            replace = 'y';
                                            break;
                                    }
                                }
                                frame.setChar(i, j, replace);
                            }
                            //sets all the values of the frame to the value that the user chooses
                        }
                        footage.add(frame);
                        int newFrameNumber = footage.getNumFrames() + 1;
                        footage.setNumFrames(newFrameNumber);
                    }
                }catch(NumberFormatException e){
                    AlertBox.display("Error", "Number not entered in frame number box");
                }
                AlertBox.display("Frame Added", "The frame has been added to the animation, please " +
                        "start and stop the animation for the changes to take effect");
                //todo make it start and stop automatically
            });

            //cancel button
            cancel.setOnAction(event -> window.close());

            GridPane.setConstraints(numFrame,0,0);
            GridPane.setConstraints(numFrameLabel, 1,0);

            GridPane.setConstraints(charSet,0,1);
            GridPane.setConstraints(colourSet,1,1);

            GridPane.setConstraints(set,0,2);
            GridPane.setConstraints(cancel,1,2);

            grid.getChildren().addAll(numFrame,numFrameLabel,charSet,colourSet,set,cancel);

            Scene scene = new Scene(grid);

            window.setScene(scene);
            window.showAndWait();
        });
    }

    public boolean isTransformationsDone() {
        return transformationsDone;
    }

    public void setTransformationsDone(boolean transformationsDone) {
        this.transformationsDone = transformationsDone;
    }
}
