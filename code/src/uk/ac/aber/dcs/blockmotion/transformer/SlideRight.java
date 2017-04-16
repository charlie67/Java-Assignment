package uk.ac.aber.dcs.blockmotion.transformer;

import uk.ac.aber.dcs.blockmotion.model.IFrame;

/**
 * @author Charlie
 * @version 16.4.17
 */
public class SlideRight extends Transform implements Transformer {

    @Override
    public void transform(IFrame frame) {
        super.initialize(frame.getNumRows(), frame);

        for (int i = 0; i<super.numRows; i++){
            for (int j =0; j<super.numRows; j++){
                if (j-1<super.leftMostColumn){
                    frame.setChar(i, j, super.tempFrame.getChar(i, super.rightMostColumn));
                }else {
                    frame.setChar(i, j, super.tempFrame.getChar(i, j - 1));
                }
            }
        }
    }
}
