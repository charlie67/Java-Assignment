package uk.ac.aber.dcs.blockmotion.model;


import uk.ac.aber.dcs.blockmotion.transformer.Transformer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Charlie Robinson
 * @version 27.4.17
 */
public class Footage implements IFootage {
    private int numFrames;
    private int numRows; //rows are the same as columns
    private ArrayList<IFrame> frames = new ArrayList<>();


    @Override
    public int getNumFrames() {
        return numFrames;
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    @Override
    public IFrame getFrame(int num) {
        return frames.get(num);
    }

    @Override
    public void add(IFrame f) {
        frames.add(f);
    }

    @Override
    public void load(String fn) throws IOException {
        try (Scanner infile = new Scanner(new FileReader(fn))) {
            frames.clear();
            System.out.println("loading footage file");
            infile.useDelimiter(":|\r?\n|\r");
            numFrames = infile.nextInt();
            numRows = infile.nextInt();
            infile.nextLine(); //clear any EOL characters
            /*
            the loading file, after these two numbers, consists of numFrams*numRows number of lines
            with each set of numRows being the rows for each frame
             */

            for (int z = 0; z < numFrames; z++) {
                Frame fr = new Frame(numRows);
                //the top of the file relates to the bottom of the frame

                for (int i = 0; i < numRows; i++) {//rows is the same as columns so this works
                    String line = infile.nextLine();
                    for (int j = 0; j<numRows; j++) {
                        fr.setChar(numRows-1-i, j, line.charAt(j));
                    }
                }
                this.add(fr);

            }
        }
    }

    @Override
    public void save(String fn) {
        try (PrintWriter outfile = new PrintWriter(new FileWriter(fn))) {

            outfile.println(numFrames);
            outfile.println(numRows);

            for(IFrame f: frames){
                f.tofile(outfile);
            }

        } catch (FileNotFoundException fnfe){
            System.err.println("File not found, please try again");

        } catch (IOException ioe) {
            System.err.println("File error, please try again.");
        }

    }

    @Override
    public void transform(Transformer t) {

        for (IFrame f: frames){
            t.transform(f);
        }
        //for each frame f, transform it using the supplied transformation
    }

    @Override
    public void transform(Transformer t, int frame){
        t.transform(frames.get(frame));
    }

    /**
     * this method was used to test that the loading was working properly
     */
    public void printChar() {
        for (int i = 0; i < numFrames; i++) {
            for (int j = 0; j < numRows; j++) {
                for (int z = 0; z < numRows; z++) {
                    System.out.print(frames.get(i).getChar(j, z));
                }
                System.out.println("");
            }
        }

    }

    public void setNumFrames(int num){
        numFrames = num;

        //todo test this
        //I was adding huge amounts of frames to an animation and this was causing the
        //numFrames int to become negative, this should fix it
        if (numFrames < 0){
            numFrames = 1;
//            System.out.println("numFrames reset");
        }
    }
}