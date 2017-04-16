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
    int topRow;
    int botRow;
    Frame tempFrame;

    private boolean initializeDone = false;

    public void initialize(int rows, IFrame frame){
        //need to make sure you can't initialize twice
        if (!initializeDone) {
            initializeDone = true;
            numRows = rows;
            rightMostColumn = numRows - 1;
            botRow = 0;
            topRow = numRows-1;
            tempFrame = new Frame();
            tempFrame.setBlockSize(numRows);
        }

        for(int i=0; i<numRows;i++){
            for(int j=0; j<numRows;j++){
                tempFrame.setChar(i,j,frame.getChar(i,j));
                //this should solve the problem discussed in slideLeft
            }
        }
    }


    @Override
    public void transform(IFrame frame) {
        System.err.println("Transformation not implemented yet");
    }
}
