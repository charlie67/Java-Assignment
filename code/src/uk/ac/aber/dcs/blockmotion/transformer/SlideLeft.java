package uk.ac.aber.dcs.blockmotion.transformer;

import uk.ac.aber.dcs.blockmotion.model.IFrame;

/**
 * Created by charl on 15/04/2017.
 */
public class SlideLeft extends Transform implements Transformer {
    /*
    this is probably very similar to SlideRight
     */
    @Override
    public void transform(IFrame frame){
        super.numRows = frame.getNumRows();

        char[] temp = new char[super.numRows];

        /*left mose frame has column 0
        right most frame is frame.getNumRows()-1
        will need a temp char[] to store the row while the move is being done
         */

        for (int j = 0; j<super.numRows; j++){

            //want the row to be 0 columns to change
            temp[j] = frame.getChar(0, j);
        }
        System.out.println(temp);
    }
}
