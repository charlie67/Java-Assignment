package uk.ac.aber.dcs.blockmotion.model;

import uk.ac.aber.dcs.blockmotion.transformer.Transformer;

import java.io.IOException;

/**
 * Represents the animation footage. You must implement this with a
 * class. YOU MUST NOT CHANGE THIS INTERFACE!
 * @author Chris Loftus
 * @version 1.0, 14th March 2017
 */
public abstract interface IFootage {
    /**
     * Get the number frames that make up the footage
     * @return the number of frames
     */
    public abstract int getNumFrames();

    /**
     * Get the number of rows that make up a frame in the footage
     * @return the number of frame rows.
     */
    public abstract int getNumRows();

    /**
     * Get the given frame
     * @param num the position of the frame starting from 0
     * @return the frame
     * @throws IllegalArgumentException if frame num does not exist
     */
    public abstract IFrame getFrame(int num);

    /**
     * Adds a new frame to the footage increasing the num frames by one
     * @param f the frame to add
     * @throws IllegalArgumentException if f is null
     */
    public abstract void add(IFrame f);

    /**
     * Load the frames from the given file
     * @param fn the file name
     * @throws IOException thrown if the file cannot be opened, e.g. it does not exist
     */
    public abstract void load(String fn) throws IOException;

    /**
     * Save the footage to the given file
     * @param fn the file name
     * @throws IOException thrown if the file cannot be created or saved to.
     */
    public abstract void save(String fn) throws IOException;

    /**
     * Undertake a transformation on every frame in the footage
     * @param t the transformer
     */
    public abstract void transform(Transformer t);

    /**
     * Will transform a specific frame instead of everything.
     * @param t the transformer
     * @param frame the frame to transform
     */
    public void transform(Transformer t, int frame);

    /**
     * Method used to test my loading function
     */
    public abstract void printChar();

    /**
     * method to set the number of frames
     * this is used when a new frame is added to the frames array list
     */
    public abstract void setNumFrames(int numFrame);

}
