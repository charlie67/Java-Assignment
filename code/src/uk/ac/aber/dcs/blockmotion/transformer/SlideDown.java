package uk.ac.aber.dcs.blockmotion.transformer;

import uk.ac.aber.dcs.blockmotion.model.IFrame;

/**
 * @author Charlie Robinson
 * @version 16.4.17
 */
public class SlideDown extends Transform implements Transformer {

    @Override
    public void transform(IFrame frame){
        super.initialize(frame.getNumRows(), frame);

        for(int i =0; i<super.numRows; i++){
            for (int j =0; j<numRows; j++){

                if(i-1<super.botRow){

                    frame.setChar(i,j,tempFrame.getChar(super.topRow, j));
                }else {

                    frame.setChar(i, j, tempFrame.getChar(i - 1, j));
                }
            }
        }
    }
}
