package uk.ac.aber.dcs.blockmotion.model;

import java.io.PrintWriter;

/**
 * @author Charlie Robinson
 * @verison 16.4.17
 */
public class Frame implements IFrame {
    private char[][] blocks;
    private int numRows;

    public Frame(){

    }

    @Override
    public void insertLines(String[] lines) {
    }

    @Override
    public int getNumRows() {
        return numRows;

    }

    @Override
    public void tofile(PrintWriter outfile) {
        //TODO test this
        for (int i = 0; i < numRows; i++) {//rows is the same as columns so this works
            for (int j = 0; j<numRows; j++) {
                outfile.print(getChar(i,j));
            }
            outfile.print('\n');
        }

    }

    @Override
    public char getChar(int i, int j) {
        return blocks[i][j];
    }

    @Override
    public void setChar(int i, int j, char ch) {
        blocks[i][j] = ch;
    }

    @Override
    public IFrame copy() {
        return this;
        //does this work?
    }

    @Override
    public void replace(IFrame f) {
        //cast f to a Frame and then
        //this = f
        //???
        //TODO frame.replace() method
    }

    /**
     * will initialize the char array and set the array to be the correct size
     * @param size the number of rows or columns
     */
    public void setBlockSize(int size){
        numRows = size;
        blocks = new char[size][size];
    }
}
