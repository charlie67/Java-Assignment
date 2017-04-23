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
 * @version 20.4.17
 */
public class TransformMenu {
    private Transform transformer;
    private boolean transformationsDone = false;

    /* these are here because otherwise I was getting
     Variable used in lambda expression should be final or effectively final
     in the lambda for button.setOnAction()
     */
    private int slideLeftNumber = 1;
    private int slideRightNumber = 1;
    private int slideUpNumber = 1;
    private int slideDownNumber = 1;


    public void display(IFootage footage) {
        Platform.runLater(() -> {
            Stage window = new Stage();

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(8);
            grid.setHgap(10);

            window.setTitle("Transformation Menu");

            TextField slideNumber = new TextField("1");
            TextField frameNumber = new TextField("1");

            CheckBox checkBox = new CheckBox("All Frames");

            Button slideLeftButton = new Button();
            Button slideRightButton = new Button();
            Button slideUpButton = new Button();
            Button slideDownButton = new Button();
            Button flipVerticalButton = new Button();
            Button flipHorizontalButton = new Button();

            Label slideDescription = new Label("Slide amount");

            //slide left button
            slideLeftButton.setText("Slide Left");
            slideLeftButton.setOnAction(event -> {
                transformer = new SlideLeft();
                slideLeftNumber = Integer.parseInt(slideNumber.getText());
                transformationsDone = true;
                
                footage.transform(transformer);

            });

            //slide right button
            slideRightButton.setText("Slide Right");
            slideRightButton.setOnAction(event -> {
                transformer = new SlideRight();
                slideRightNumber = Integer.parseInt(slideNumber.getText());
                transformationsDone = true;

                footage.transform(transformer);

            });

            //slide up button
            slideUpButton.setText("Slide Up");
            slideUpButton.setOnAction(event -> {
                transformer = new SlideUp();
                slideUpNumber = Integer.parseInt(slideNumber.getText());
                transformationsDone = true;

                footage.transform(transformer);

            });

            //slide down button
            slideDownButton.setText("Slide Down");
            slideDownButton.setOnAction(event -> {
                transformer = new SlideDown();
                slideDownNumber = Integer.parseInt(slideNumber.getText());
                transformationsDone = true;

                footage.transform(transformer);

            });

            //flip vertical button
            flipVerticalButton.setText("Vertical Flip");
            flipVerticalButton.setOnAction(event -> {
                transformer = new FlipVertical();
                transformationsDone = true;

                footage.transform(transformer);

            });

            //flip horizontal button
            flipHorizontalButton.setText("Horizontal Flip");
            flipHorizontalButton.setOnAction(event -> {
                transformer = new FlipHorizontal();
                transformationsDone = true;

                footage.transform(transformer);

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


            grid.add(slideDescription, 1,1,2,1);

            grid.add(slideNumber, 3,1,2,1);

            grid.getChildren().addAll(slideLeftButton, slideRightButton, slideUpButton, slideDownButton,
                    flipVerticalButton, flipHorizontalButton);
            //

            Scene scene = new Scene(grid);

            window.setScene(scene);
            window.showAndWait();
        });
    }
}
