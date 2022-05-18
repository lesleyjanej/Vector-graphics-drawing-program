package model;

/**
 * This class creates an ellipse, a subclass of shape.
 * An ellipse is two-dimensional and therefore has a height and width.
 *
 * @author Lesleyjane J https://github.com/lesleyjanej
 */
public class Ellipse extends Shape {

    /**
     * To instantiate an ellipse object, specify the start and end coordinates.
     * @param startX start coordinate on x axis
     * @param startY start coordinate on y axis
     * @param endX end coordinate on x axis
     * @param endY end coordinate on y axis
     */
    public Ellipse(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
    }

    /**
     * X coordinate for the start of the ellipse
     * @return int X coordinate
     */
    public int getX() {
        return Math.min(startX, endX);
    }

    /**
     * Y coordinate for the end of the ellipse.
     * @return int Y coordinate
     */
    public int getY() {
        return Math.min(startY, endY);
    }

    /**
     * Find the height of the ellipse.
     * @return int height of ellipse.
     */
    public int getHeight() {
        return Math.max(startY, endY) - getY();
    }

    /**
     * Find the width of the ellipse.
     * @return int width of ellipse.
     */
    public int getWidth() {
        return Math.max(startX, endX) - getX();
    }
}
