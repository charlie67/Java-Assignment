package uk.ac.aber.dcs.blockmotion.transformer;

import uk.ac.aber.dcs.blockmotion.model.IFrame;

/**
 * @author Charlie Robinson
 * @version 17.4.17
 */
public class FlipHorizontal extends Transform implements Transformer{

    @Override
    public void transform(IFrame frame) {
        super.initialize(frame.getNumRows(), frame);

        /*
        column leftMostColumn goes to column rightMostColumn
        leftMostColumn+1 goes to rightMostColumn-1
        leftMostColumn+j goes to rightMostColumn-j
         */
        for (int i =0; i<numRows; i++){
            for (int j=0; j<numRows; j++){
                frame.setChar(i,j,tempFrame.getChar(i,rightMostColumn-j));
            }
        }
    }
}
