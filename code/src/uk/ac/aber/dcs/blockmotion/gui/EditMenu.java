package uk.ac.aber.dcs.blockmotion.gui;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uk.ac.aber.dcs.blockmotion.model.IFootage;

/**
 * @author Charlie Robinson
 * @version 20.4.17
 */
public class EditMenu {
    private int frameNum;
    private int maxFrameNum;//the number of frame the footage has
    private int row;
    private int column;
    private int maxRow;//the highest number of rows and columns there are
    private char toReplace;

    public void display(IFootage footage){
        Platform.runLater(() -> {
            maxFrameNum = footage.getNumFrames();
            maxRow = footage.getNumRows();

            Stage window = new Stage();

            window.setTitle("Edit Menu");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(8);
            grid.setHgap(10);

            TextField frameNumber = new TextField();
            TextField columnNumber = new TextField();
            TextField rowNumber = new TextField();

            ChoiceBox<String> charReplace = new ChoiceBox<>();

            Button set = new Button();
            Button cancel = new Button();

            charReplace.getItems().addAll("b - blue", "l - yellow", "r - black");
            charReplace.setValue("b - blue");

            //set button
            set.setText("Set");
            set.setOnAction(event -> {
                frameNum = Integer.parseInt(frameNumber.getText())-1;
                row = Integer.parseInt(rowNumber.getText())-1;
                column = Integer.parseInt(columnNumber.getText())-1;
                toReplace = charReplace.getValue().charAt(0);
//                toReplace;
                //all of these are 0 based so need to take away 1

                if (validateData(column, row, frameNum, columnNumber, rowNumber, frameNumber)) {
                    //all correct so proceed with the change
                    footage.getFrame(frameNum).setChar(row,column,toReplace);
                    AlertBox.display("Change Done", "Done!");
                }
            });

            //cancel button
            cancel.setText("Cancel");
            cancel.setOnAction(event -> window.close());

            /*
            Again the GridPane.setHalignment() code came from
            https://stackoverflow.com/questions/35438104/javafx-alignment-of-label-in-gridpane
            on the 18.4.17
             */
            GridPane.setConstraints(frameNumber, 0, 0);

            GridPane.setConstraints(columnNumber,0,1);

            GridPane.setConstraints(rowNumber,0,2);

            GridPane.setConstraints(charReplace,0,3);
            GridPane.setHalignment(charReplace, HPos.CENTER);

            GridPane.setConstraints(set,0,4);
            GridPane.setHalignment(set, HPos.CENTER);

            GridPane.setConstraints(cancel,1,4);
            GridPane.setHalignment(cancel, HPos.CENTER);

            grid.getChildren().setAll(frameNumber,columnNumber,rowNumber,charReplace,set,cancel);

            Scene scene = new Scene(grid);

            window.setScene(scene);
            window.showAndWait();
        });
    }

    private boolean validateData(int column, int row, int frameNum, TextField columnNumber, TextField rowNumber, TextField frameNumber){
        //returns true of all the data is ok and the footage can be set
        if (column>=maxRow){
            AlertBox.display("Column Error", columnNumber.getText() + " is too large, please enter a number no bigger than " + maxRow);
            return false;
        } else if (row>=maxRow){
            AlertBox.display("Row Error", rowNumber.getText() + " is too large, please enter a number no bigger than " + maxRow);
            return false;
        } else if (frameNum>=maxFrameNum){
            AlertBox.display("Frame Error", frameNumber.getText() + " is too large, please enter a number no bigger than " + maxFrameNum);
            return false;
        } else {
            return true;
        }
    }
}