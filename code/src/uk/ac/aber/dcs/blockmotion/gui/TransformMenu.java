package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uk.ac.aber.dcs.blockmotion.model.IFootage;
import uk.ac.aber.dcs.blockmotion.transformer.*;

/**
 * @author Charlie
 * @version 18.4.17
 */
public class TransformMenu {
    private Transform transformer;

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

            window.setTitle("Edit Menu");

            TextField slideLeftText = new TextField("1");
            TextField slideRightText = new TextField("1");
            TextField slideUpText = new TextField("1");
            TextField slideDownText = new TextField("1");

            Button slideLeftButton = new Button();
            Button slideRightButton = new Button();
            Button slideUpButton = new Button();
            Button slideDownButton = new Button();
            Button flipVerticalButton = new Button();
            Button flipHorizontalButton = new Button();

            //slide left button
            slideLeftButton.setText("Slide Left");
            slideLeftButton.setOnAction(event -> {
                transformer = new SlideLeft();
                slideLeftNumber = Integer.parseInt(slideLeftText.getText());

                for (int i = 0; i < slideLeftNumber; i++) {
                    footage.transform(transformer);
                }
            });

            //slide right button
            slideRightButton.setText("Slide Right");
            slideRightButton.setOnAction(event -> {
                transformer = new SlideRight();
                slideRightNumber = Integer.parseInt(slideRightText.getText());

                for (int i = 0; i < slideRightNumber; i++) {
                    footage.transform(transformer);
                }
            });

            //slide up button
            slideUpButton.setText("Slide Up");
            slideUpButton.setOnAction(event -> {
                transformer = new SlideUp();
                slideUpNumber = Integer.parseInt(slideRightText.getText());

                for (int i = 0; i < slideUpNumber; i++) {
                    footage.transform(transformer);
                }
            });

            //slide down button
            slideDownButton.setText("Slide Down");
            slideDownButton.setOnAction(event -> {
                transformer = new SlideDown();
                slideDownNumber = Integer.parseInt(slideDownText.getText());

                for (int i = 0; i < slideDownNumber; i++) {
                    footage.transform(transformer);
                }
            });

            //flip vertical button
            flipVerticalButton.setText("Vertical Flip");
            flipVerticalButton.setOnAction(event -> {
                transformer = new FlipVertical();
                footage.transform(transformer);
            });

            //flip horizontal button
            flipHorizontalButton.setText("Horizontal Flip");
            flipHorizontalButton.setOnAction(event -> {
                transformer = new FlipHorizontal();
                footage.transform(transformer);
            });

            GridPane.setConstraints(slideLeftButton, 0, 0);
            GridPane.setHalignment(slideLeftButton, HPos.CENTER);

            GridPane.setConstraints(slideLeftText, 1, 0);
            GridPane.setHalignment(slideLeftText, HPos.CENTER);


            GridPane.setConstraints(slideRightButton, 0, 1);
            GridPane.setHalignment(slideRightButton, HPos.CENTER);

            GridPane.setConstraints(slideRightText, 1, 1);
            GridPane.setHalignment(slideRightText, HPos.CENTER);


            GridPane.setConstraints(slideUpButton, 0, 2);
            GridPane.setHalignment(slideUpButton, HPos.CENTER);

            GridPane.setConstraints(slideUpText, 1, 2);
            GridPane.setHalignment(slideUpText, HPos.CENTER);


            GridPane.setConstraints(slideDownButton, 0, 3);
            GridPane.setHalignment(slideDownButton, HPos.CENTER);

            GridPane.setConstraints(slideDownText, 1, 3);
            GridPane.setHalignment(slideDownText, HPos.CENTER);


            GridPane.setConstraints(flipVerticalButton, 0, 4);
            GridPane.setHalignment(flipVerticalButton, HPos.CENTER);

            GridPane.setConstraints(flipHorizontalButton, 0, 5);
            GridPane.setHalignment(flipHorizontalButton, HPos.CENTER);

            grid.getChildren().addAll(slideLeftButton, slideRightButton, slideUpButton, slideDownButton,
                    slideLeftText, slideRightText, slideDownText, slideUpText, flipVerticalButton, flipHorizontalButton);


            Scene scene = new Scene(grid);

            window.setScene(scene);
            window.showAndWait();
        });
    }
}
