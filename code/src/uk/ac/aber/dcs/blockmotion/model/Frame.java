package uk.ac.aber.dcs.blockmotion.model;

import java.io.PrintWriter;

/**
 * @author Charlie Robinson
 * @verison 13.4.17
 */
public class Frame implements IFrame {
    private char[][] blocks;
    private int numRows;


    @Override
    public void insertLines(String[] lines) {
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    @Override
    public int setNumRows(int numRows){
        this.
    }

    @Override
    public void tofile(PrintWriter outfile) {

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
        return null;
    }

    @Override
    public void replace(IFrame f) {

    }

    /**
     * will initialize the char array and set the array to be the correct size
     * @param size the number of rows or columns
     */
    public void setBlockSize(int size){
        blocks = new char[size][size];
    }
}
