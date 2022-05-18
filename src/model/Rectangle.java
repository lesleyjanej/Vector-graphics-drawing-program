package model;

/**
 * Creates a two-dimensional rectangle, a subclass of shape.
 *
 * @author Lesleyjane J https://github.com/lesleyjanej
 */
public class Rectangle extends Shape {

    /**
     * To instantiate a rectangle, specify the start and end coordinates.
     * @param startX start coordinate on x axis
     * @param startY start coordinate on y axis
     * @param endX end coordinate on x axis
     * @param endY end coordinate on y axis
     */
    public Rectangle(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
    }

    /**
     * X coordinate for the start of the rectangle.
     * @return int X coordinate
     */
    public int getX() {
        return Math.min(startX, endX);
    }

    /**
     * Y coordinate for the end of the rectangle.
     * @return int Y coordinate
     */
    public int getY() {
        return Math.min(startY, endY);
    }

    /**
     * Find the height of the rectangle.
     * @return int height of rectangle.
     */
    public int getHeight() {
        return Math.max(startY, endY) - getY();
    }

    /**
     * Find the width of the rectangle.
     * @return int width of rectangle.
     */
    public int getWidth() {
        return Math.max(startX, endX) - getX();
    }


}
