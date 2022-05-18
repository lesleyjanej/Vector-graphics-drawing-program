package model;

/**
 * An abstract class which outlines how a Shape should be created.
 * All shapes have start and end coordinates, a border colour and a fill colour.
 * The border and fill colour are set to black by default.
 *
 * @author Lesleyjane J https://github.com/lesleyjanej
 */
public abstract class Shape {
    private SimpleColour borderColour;
    private SimpleColour fillColour;
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;

    /**
     * To instantiate a shape object, specify the start and end coordinates.
     * @param startX start coordinate on x axis
     * @param startY start coordinate on y axis
     * @param endX end coordinate on x axis
     * @param endY end coordinate on y axis
     */
    public Shape (int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        borderColour = new SimpleColour(0,0,0); // black by default
        fillColour = new SimpleColour(0,0,0); // black by default

    }

    /**
     * Get the border colour of the shape.
     * @return borderColour.
     */
    public SimpleColour getBorderColour() {
        return borderColour;
    }

    /**
     * Get the fill colour of the shape.
     * @return fillColour.
     */
    public SimpleColour getFillColour() {
        return fillColour;
    }

    /**
     * Get the start x coordinate for the shape.
     * @return startX.
     */
    public int getStartX() {
        return startX;
    }

    /**
     * Get the start y coordinate for the shape.
     * @return startY.
     */
    public int getStartY() {
        return startY;
    }

    /**
     * Get the end x coordinate for the shape.
     * @return endY.
     */
    public int getEndX() {
        return endX;
    }

    /**
     * Get the end y coordinate for the shape.
     * @return endY.
     */
    public int getEndY() {
        return endY;
    }

    /**
     * Set the border colour the for shape given a colour.
     * @param colour An instance of SimpleColour (r,g,b colour).
     */
    public void setBorderColour(SimpleColour colour) {
        this.borderColour = colour;
    }

    /**
     * Set the fill colour the for shape given a colour.
     * @param colour An instance of SimpleColour (r,g,b colour).
     */
    public void setFillColour(SimpleColour colour) {
        this.fillColour = colour;
    }


}
