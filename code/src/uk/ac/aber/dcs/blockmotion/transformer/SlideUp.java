package uk.ac.aber.dcs.blockmotion.transformer;

import uk.ac.aber.dcs.blockmotion.model.IFrame;

/**
 * @author Charlie Robinson
 * @version 16.4.17
 */
public class SlideUp extends Transform implements Transformer {

    @Override
    public void transform(IFrame frame) {
        super.initialize(frame.getNumRows(),frame);

        /*
        row 1 to go to row 0 etc

        again will need 2 for loops
         */

        for(int i =0; i<super.numRows; i++){
            for (int j =0; j<numRows; j++){

                if(i+1>super.topRow){

                    frame.setChar(i,j,tempFrame.getChar(super.botRow, j));
                }else {

                    frame.setChar(i, j, tempFrame.getChar(i + 1, j));
                }
            }
        }
    }
}
