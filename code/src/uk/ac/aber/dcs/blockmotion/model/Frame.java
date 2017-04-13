package uk.ac.aber.dcs.blockmotion.model;

import java.io.PrintWriter;

/**
 * Created by charl on 12/04/2017.
 */
public class Frame implements IFrame {

    @Override
    public void insertLines(String[] lines) {

    }

    @Override
    public int getNumRows() {
        return 0;
    }

    @Override
    public void tofile(PrintWriter outfile) {

    }

    @Override
    public char getChar(int i, int j) {
        return 0;
    }

    @Override
    public void setChar(int i, int j, char ch) {

    }

    @Override
    public IFrame copy() {
        return null;
    }

    @Override
    public void replace(IFrame f) {

    }
}
