package model;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * The DrawingHandler class is responsible for storing the user's vector graphics,
 * including the border colour and fill colour.
 * The stored graphics may be undone (removed) and redone (re-added).
 *
 * @author Lesleyjane J https://github.com/lesleyjanej
 */
public class DrawingHandler {
    private Shape currentShape; // shape currently being drawn
    private SimpleColour chosenBorderColour; // selected border colour
    private SimpleColour chosenFillColour; // selected fill colour
    private Stack<model.Shape> shapes;
    private Stack<model.Shape> undoList;

    /**
     * Create a new DrawingHandler. Border colour and fill colour are set to black by default.
     */
    public DrawingHandler() {
        currentShape = null; // null until a new shape is drawn
        shapes = new Stack<>();
        undoList = new Stack<>();
        chosenBorderColour = new SimpleColour(0,0,0); // black by default
        chosenFillColour = new SimpleColour(0,0,0); // black by default
    }

    /**
     * Add a new shape to the stack of shapes.
     * @param shape - the new shape to be added.
     */
    public void addShape(Shape shape) {
        shapes.push(shape);
    }

    /**
     * Get the stack of shapes.
     * @return stack of shapes.
     */
    public Stack<Shape> getShapes() {
        return shapes;
    }

    /**
     * Attempt to undo the last created Shape by removing it from the stack.
     * The removed shape will be added to the undoList. redo() will allow for re-adding the shape.
     * @return null if there are no shapes to be removed from the stack, otherwise returns the removed shape.
     */
    public Shape undo() {
        try {
            Shape removedShape = shapes.pop(); // remove and return last added shape
            currentShape = null; // current shape has been removed, set to null
            undoList.add(removedShape); // add removed shape to undoList
            return removedShape;
        } catch (EmptyStackException e) { //Stack is empty
            return null;
        }
    }

    /**
     * Attempt to re-add the last removed shape by getting it from the undoList and adding
     * it back to the shapes list.
     * @return null if the undoList is empty, otherwise returns the last removed shape.
     */
    public Shape redo() {
        try {
            Shape lastRemovedShape = undoList.pop(); // remove and return last shape to be removed
            shapes.add(lastRemovedShape); // add shape back to shapes list
            return lastRemovedShape;
        } catch (EmptyStackException e) { // Stack is empty
            return null;
        }
    }

    /**
     * Delete all currently stored user shapes.
     */
    public void clearShapes() {
        this.shapes.clear();
        currentShape = null;
    }

    /**
     * Sets a new shape to be the current shape that the user is drawing.
     * @param currentShape the new shape.
     */
    public void setCurrentShape(Shape currentShape) {
        this.currentShape = currentShape;
    }

    /**
     * The user can create a new shape, it becomes the new current shape
     * and has a border colour and a fill colour.
     * @param newShape the new shape which the user has drawn.
     */
    public void newShape(Shape newShape) {
        setCurrentShape(newShape);
        currentShape.setBorderColour(chosenBorderColour);
        currentShape.setFillColour(chosenFillColour);
    }

    /**
     * Get the user's current shape.
     * @return currentShape.
     */
    public Shape getCurrentShape() {
        return currentShape;
    }

    /**
     * Set the border colour. New shapes will have this border colour.
     * @param chosenBorderColour the selected border colour.
     */
    public void setChosenBorderColour(SimpleColour chosenBorderColour) {
        this.chosenBorderColour = chosenBorderColour;
    }

    /**
     * Set the fill colour. New shapes will have this fill colour.
     * @param chosenFillColour the selected fill colour.
     */
    public void setChosenFillColour(SimpleColour chosenFillColour) {
        this.chosenFillColour = chosenFillColour;
    }

    /**
     * Get the current border colour.
     * @return chosenBorderColour.
     */
    public SimpleColour getChosenBorderColour() {
        return chosenBorderColour;
    }

    /**
     * Get the current fill colour.
     * @return chosenFillColour.
     */
    public SimpleColour getChosenFillColour() {
        return chosenFillColour;
    }
}
