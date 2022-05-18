package model;

/**
 * Creates a one-dimensional line, a subclass of shape.
 *
 * @author Lesleyjane J https://github.com/lesleyjanej
 */
public class Line extends Shape {

    /**
     * To instantiate a line object, specify the start and end coordinates.
     * @param startX start coordinate on x axis
     * @param startY start coordinate on y axis
     * @param endX end coordinate on x axis
     * @param endY end coordinate on y axis
     */
    public Line(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
    }
}
