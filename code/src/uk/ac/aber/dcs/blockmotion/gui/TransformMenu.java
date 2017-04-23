package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uk.ac.aber.dcs.blockmotion.model.IFootage;
import uk.ac.aber.dcs.blockmotion.transformer.*;

/**
 * @author Charlie Robinson
 * @version 23.4.17
 */
public class TransformMenu {
    private Transform transformer;
    private boolean transformationsDone = false;
    private TextField frameNumber = new TextField("1");
    private CheckBox frameCheckBox = new CheckBox("All Frames");

    /* these are here because otherwise I was getting
     Variable used in lambda expression should be final or effectively final
     in the lambda for button.setOnAction()
     */
    private int slideNumberInt = 1;


    public void display(IFootage footage) {
        Platform.runLater(() -> {
            Stage window = new Stage();

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(8);
            grid.setHgap(10);

            window.setTitle("Transformation Menu");

            TextField slideNumber = new TextField("1");
            slideNumber.setPrefColumnCount(1);

            frameNumber.setPrefColumnCount(1);

            frameCheckBox.fire();

            Button slideLeftButton = new Button();
            Button slideRightButton = new Button();
            Button slideUpButton = new Button();
            Button slideDownButton = new Button();
            Button flipVerticalButton = new Button();
            Button flipHorizontalButton = new Button();

            Label slideDescription = new Label("Slide Amount");
            Label frameDescription = new Label("Frame Number");

            //slide left button
            slideLeftButton.setText("Slide Left");
            slideLeftButton.setOnAction(event -> {
                transformer = new SlideLeft();
                setSlideNumberIntInternal(slideNumber);
                transformationsDone = true;

                doTransform(transformer, footage);

            });

            //slide right button
            slideRightButton.setText("Slide Right");
            slideRightButton.setOnAction(event -> {
                transformer = new SlideRight();
                setSlideNumberIntInternal(slideNumber);
                transformationsDone = true;

                doTransform(transformer, footage);

            });

            //slide up button
            slideUpButton.setText("Slide Up");
            slideUpButton.setOnAction(event -> {
                transformer = new SlideUp();
                setSlideNumberIntInternal(slideNumber);
                transformationsDone = true;

                doTransform(transformer, footage);

            });

            //slide down button
            slideDownButton.setText("Slide Down");
            slideDownButton.setOnAction(event -> {
                transformer = new SlideDown();
                setSlideNumberIntInternal(slideNumber);
                transformationsDone = true;

                doTransform(transformer, footage);

            });

            //flip vertical button
            flipVerticalButton.setText("Vertical Flip");
            flipVerticalButton.setOnAction(event -> {
                transformer = new FlipVertical();
                transformationsDone = true;

                doTransform(transformer, footage);

            });

            //flip horizontal button
            flipHorizontalButton.setText("Horizontal Flip");
            flipHorizontalButton.setOnAction(event -> {
                transformer = new FlipHorizontal();
                transformationsDone = true;

                doTransform(transformer, footage);

            });


            GridPane.setConstraints(slideLeftButton, 0, 0);
            GridPane.setHalignment(slideLeftButton, HPos.CENTER);

            GridPane.setConstraints(slideRightButton, 1, 0);
            GridPane.setHalignment(slideRightButton, HPos.CENTER);

            GridPane.setConstraints(slideUpButton, 2, 0);
            GridPane.setHalignment(slideUpButton, HPos.CENTER);

            GridPane.setConstraints(slideDownButton, 3, 0);
            GridPane.setHalignment(slideDownButton, HPos.CENTER);


            GridPane.setConstraints(flipVerticalButton, 4, 0);
            GridPane.setHalignment(flipVerticalButton, HPos.CENTER);

            GridPane.setConstraints(flipHorizontalButton, 5, 0);
            GridPane.setHalignment(flipHorizontalButton, HPos.CENTER);


            grid.add(slideDescription, 0,1);
            grid.add(slideNumber, 1,1);

            grid.add(frameDescription, 3,1);
            grid.add(frameNumber, 4,1);
            grid.add(frameCheckBox, 5,1);

            grid.getChildren().addAll(slideLeftButton, slideRightButton, slideUpButton, slideDownButton,
                    flipVerticalButton, flipHorizontalButton);
            //

            Scene scene = new Scene(grid);

            window.setScene(scene);
            window.showAndWait();
        });
    }

    private void setSlideNumberIntInternal(TextField slideNumber) {
        try {
            slideNumberInt = Integer.parseInt(slideNumber.getText());
        } catch(NumberFormatException e){
            AlertBox.display("Error", "Number not entered into slide number box");
        }
    }

    public boolean isTransformationsDone() {
        return transformationsDone;
    }

    public void setTransformationsDone(boolean transformationsDone) {
        this.transformationsDone = transformationsDone;
    }

    private void doTransform(Transformer transformer, IFootage footage){
        if (frameCheckBox.isSelected()) {
            footage.transform(transformer);
        } else {
            footage.transform(transformer, getFrameNumber(footage));
        }

    }

    private int getFrameNumber(IFootage footage){
        try {
            int toReturn = Math.abs(Integer.parseInt(frameNumber.getText()));
            if (toReturn>footage.getNumFrames()){
                AlertBox.display("Error", "Frame number entered was too high, the max number is " + footage.getNumFrames() + ", transforming frame 1");
                return 0;
            }
            toReturn--;//frames are 0 based so need to -1
            return toReturn;
        } catch (NumberFormatException e) {
            AlertBox.display("Error", "Number not entered into text box, transforming frame 1");
            return 0;
        }
    }
}
