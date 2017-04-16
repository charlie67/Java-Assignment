package uk.ac.aber.dcs.blockmotion.transformer;

import uk.ac.aber.dcs.blockmotion.model.Frame;
import uk.ac.aber.dcs.blockmotion.model.IFrame;

/**
 * @author Charlie Robinson
 * @version 16.04.17
 */
public class SlideLeft extends Transform implements Transformer {
    /*
    this is probably very similar to SlideRight
     */
    @Override
    public void transform(IFrame frame){
        super.initialize(frame.getNumRows());

        IFrame tempFrame = new Frame();
        tempFrame = frame.copy();

        /*
        tempFrame is the one that is never changed just there to copy from so that it never change

        left mose frame has column 0
        right most frame is frame.getNumRows()-1
        will need a temp char[] to store the row while the move is being done

        to get a column can do:
        for (int j = 0; j<super.numRows; j++){

            //want the row to be 0 columns to change
            temp[j] = frame.getChar(0, j);
        }

        where temp[j] is char[super.numRows]

        want to read tempFrame column 0 write that to column super.numRows-1 of original frames

        for (int i = 0; i<numRows; i++){
            //this will write the left column of the frame to its right most column
            frame.setChar(i,rightMostColumn, tempFrame.getChar(i,leftMostColumn));
        }

        will need a for loop to do this for all the columns

         */

        for (int i = 0; i<numRows; i++){
            for (int j =0; j<numRows; j++){
                /*
                set column j row i to be column j+1 row i
                j needs to wrap around

                 need something to calculate this wraparound */

                if(j+1>super.rightMostColumn){
                    //use 0 as the column because that's what comes after the highest column
                    frame.setChar(i, j, tempFrame.getChar(i, 0));
                } else {

                    frame.setChar(i, j, tempFrame.getChar(i, j + 1));
                }
            }
        }






    }
}
