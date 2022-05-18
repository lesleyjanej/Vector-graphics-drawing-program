package model;

/**
 * The SimpleColour class represents an RGB colour
 * by storing the integer values of r,g,b for the colour.
 *
 * @author Lesleyjane J https://github.com/lesleyjanej
 */
public class SimpleColour {
    private int r;
    private int g;
    private int b;

    /**
     * Create a new SimpleColour by specifying the int r,g,b values.
     * @param r red int value for colour
     * @param g green int value for colour
     * @param b blue int value for colour
     */
    public SimpleColour(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Get red value for colour.
     * @return int r.
     */
    public int getR() {
        return r;
    }

    /**
     * Get green value for colour.
     * @return int g.
     */
    public int getG() {
        return g;
    }

    /**
     * Get blue value for colour.
     * @return int b.
     */
    public int getB() {
        return b;
    }
}
