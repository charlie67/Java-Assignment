package uk.ac.aber.dcs.blockmotion.transformer;

import uk.ac.aber.dcs.blockmotion.model.Frame;
import uk.ac.aber.dcs.blockmotion.model.IFrame;

/**
 * @author Charlie Robinson
 * @version 16/04/2017.
 */
public class Transform implements Transformer{
    int numRows;
    int leftMostColumn = 0; //this is always 0
    int rightMostColumn;
    Frame tempFrame;

    private boolean initializeDone = false;

    public void initialize(int rows){
        //need to make sure you can't initialize twice
        if (!initializeDone) {
            initializeDone = true;
            numRows = rows;
            rightMostColumn = numRows - 1;
            tempFrame = new Frame();
            tempFrame.setBlockSize(numRows);
        }
    }


    @Override
    public void transform(IFrame frame) {
        System.err.println("Transformation not implemented yet");
    }
}
