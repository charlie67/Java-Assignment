package uk.ac.aber.dcs.blockmotion.transformer;

import uk.ac.aber.dcs.blockmotion.model.IFrame;

/**
 * @author Charlie Robinson
 * @version 16/04/2017.
 */
public class Transform implements Transformer{
    int numRows;
    int leftMostColumn = 0; //this is always 0
    int rightMostColumn;

    public void initialize(int rows){
        numRows = rows;
        rightMostColumn = numRows-1;
    }


    @Override
    public void transform(IFrame frame) {
        System.err.println("Transformation not implemented yet");
    }
}
