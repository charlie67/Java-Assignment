package uk.ac.aber.dcs.blockmotion.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @version 13.4.17
 * @author Charlie Robinson
 */
public class Footage implements IFootage {
    private int numFrames;
    private int numRows; //rows are the same as columns
    private ArrayList<IFrame> frames = new ArrayList<IFrame>();

    public Footage(){

    }

    @Override
    public int getNumFrames() {
        return numFrames;
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    @Override
    public IFrame getFrame(int num){
        return frames.get(num);
    }

    @Override
    public void add(IFrame f){
        frames.add(f);
    }

    @Override
    public void load(String fn){

    }

    @Override
    public void save(String fn){

    }

    @Override
    public void transform(Transformer t){

    }
}
