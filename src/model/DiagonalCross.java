package model;

/**
 * This class creates a one-dimensional diagonal cross, a subclass of shape.
 * A diagonal cross is created using two lines.
 *
 * @author Lesleyjane J https://github.com/lesleyjanej
 */
public class DiagonalCross extends Shape {

    /**
     * To instantiate a diagonal cross object, specify the start and end coordinates.
     * @param startX start coordinate on x axis
     * @param startY start coordinate on y axis
     * @param endX end coordinate on x axis
     * @param endY end coordinate on y axis
     */
    public DiagonalCross(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
    }

    /**
     * Creates the first line for the diagonal cross.
     * @return the created line.
     */
    public Line getFirstLine() {
        return new Line(startX, startY, endX, endY);
    }

    /**
     * Creates the second line for the diagonal cross, mirroring the first line, to create a cross shape.
     * @return the created line.
     */
    public Line getSecondLine() {
        return new Line(startX, endY, endX, startY);
    }
}
