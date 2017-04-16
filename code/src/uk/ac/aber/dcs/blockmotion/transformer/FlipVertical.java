package uk.ac.aber.dcs.blockmotion.transformer;

import uk.ac.aber.dcs.blockmotion.model.IFrame;

/**
 * @author Charlie
 * @version 16.4.17
 */
public class FlipVertical extends Transform implements Transformer {

    @Override
    public void transform(IFrame frame) {
        super.initialize(frame.getNumRows(), frame);

        /*
        row 0 (botRow) go to numRow-1 (topRow)
        row 1 goes to row topRow-1
        row 2 goes to topRow-2
        row i goes to topRow-i
         */
        for (int i =0; i<numRows; i++){
            for (int j=0; j<numRows; j++){
                frame.setChar(i,j,tempFrame.getChar(topRow-i,j));
            }
        }
    }
}
