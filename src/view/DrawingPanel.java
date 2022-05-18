package view;

import model.DiagonalCross;
import model.DrawingHandler;
import model.Ellipse;
import model.Line;
import model.SimpleColour;
import model.Rectangle;
import model.Shape;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Stack;

/**
 * The DrawingPanel class displays a graphical user interface to the user
 * and allows them to draw Shapes, change the border and fill colour,
 * undo and redo creation of shapes.
 * The class updates and accesses the model component. If a model component
 * changes, then the GUI will repaint to show the change to the user.
 *
 * @author Lesleyjane J https://github.com/lesleyjanej
 */
public class DrawingPanel extends JPanel {

    private Point startPoint; // The start point of a user's mouse click
    private Point endPoint; // The release of a mouse click
    private String buttonSelected; // The string of the button from the toolbar which the user selected
    private DrawingHandler dh; // to handle the drawing of the shapes, colour and undo/redo options.

    /**
     * Create a new Drawing Panel instance which uses
     * mouse listeners and mouse motion listeners to detect how Shapes should be created and drawn.
     */
    public DrawingPanel() {
        dh = new DrawingHandler();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint(); // start point for drawing a shape
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // When the user has finished drawing a shape, add the shape to the list of shapes
                if (startPoint != null && endPoint !=null) {
                    dh.addShape(dh.getCurrentShape());
                    // Repaint the GUI
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint(); //update endpoint while the mouse if being dragged
                // Create a new shape depending on which button was selected.
                // If the user clicks the line button then we will create a new line.
                switch (buttonSelected) {
                    case "lineBtn":
                        dh.newShape(new Line(startPoint.x, startPoint.y, endPoint.x, endPoint.y));
                        break;
                    case "rectangleBtn":
                        dh.newShape(new model.Rectangle(startPoint.x, startPoint.y, endPoint.x, endPoint.y));
                        break;
                    case "ellipseBtn":
                        dh.newShape(new model.Ellipse(startPoint.x, startPoint.y, endPoint.x, endPoint.y));
                        break;
                    case "diagonalCrossBtn":
                        dh.newShape(new DiagonalCross(startPoint.x, startPoint.y, endPoint.x, endPoint.y));
                        break;
                }
                repaint();
            }
            @Override
            public void mouseMoved(MouseEvent e) {}
        });
    }

    /**
     * Set preferred size of the panel
     * @return new dimension instance.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }


    /**
     * Override the paintComponent method to display the
     * user's drawn shapes to the user via the panel.
     * @param g the graphics instance that is used to paint the shapes to the screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // 2D graphics

        // Set anti aliasing for smoother graphics drawing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // list of all user drawn shapes
        Stack<Shape> drawnShapes = dh.getShapes();
        // If the list of user drawn shapes is not empty, then paint them to the GUI
        if (!drawnShapes.isEmpty()) {
            // For each saved shape that the user has drawn
            for (Shape shape : drawnShapes) {
                // Paint the border colour of each shape
                paintBorder(shape, g);
                // Check instance of shape and draw that specific shape (line, rectangle, etc).
                if (shape instanceof model.Line) {
                    drawLine(shape, g);
                } else if (shape instanceof model.Rectangle) {
                    Rectangle rect = (Rectangle) shape;
                    drawRect(rect, g);
                    // paint the fill colour of the rectangle
                    paintFill(shape, g);
                    g2d.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                } else if (shape instanceof model.Ellipse) {
                    Ellipse ellipse = (Ellipse) shape;
                    drawEllipse(ellipse, g2d);
                    // paint the fill colour of the ellipse
                    paintFill(shape, g);
                    g2d.fillOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
                } else if (shape instanceof DiagonalCross) {
                    DiagonalCross cross = (DiagonalCross) shape;
                    drawDiagonalCross(cross, g);
                }
            }
        }
        // Get the shape that the user is currently drawing by dragging their mouse
        Shape currentShape = dh.getCurrentShape();
        if (currentShape != null) {
            // Paint the border colour of each shape
            paintBorder(currentShape, g);
            if (currentShape instanceof Line) {
                drawLine(currentShape, g);
            }
            else if (currentShape instanceof Rectangle) {
                Rectangle rect = (Rectangle) currentShape;
                drawRect(rect, g);
                // paint the fill colour
                paintFill(currentShape, g);
                g2d.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
            }
            else if (currentShape instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) currentShape;
                drawEllipse(ellipse, g2d);
                // paint the fill colour
                paintFill(currentShape, g);
                g2d.fillOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
            }
            else if (currentShape instanceof DiagonalCross) {
                DiagonalCross cross = (DiagonalCross) currentShape;
                drawDiagonalCross(cross, g);
            }
        }

    }

    /**
     * Draw the line to the GUI.
     * @param shape the line which is being drawn.
     * @param g the graphics instance which is used to paint to the GUI.
     */
    private void drawLine(Shape shape, Graphics g) {
        g.drawLine(shape.getStartX(), shape.getStartY(), shape.getEndX(), shape.getEndY());
    }

    /**
     * Draw the rectangle to the GUI.
     * @param rect the rectangle which is being drawn.
     * @param g the graphics instance which is used to paint to the GUI.
     */
    private void drawRect(Rectangle rect, Graphics g) {
        g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    /**
     * Draw the ellipse to the GUI.
     * @param ellipse the ellipse which is being drawn.
     * @param g2d the 2d graphics instance which is used to paint to the GUI.
     */
    private void drawEllipse(Ellipse ellipse, Graphics2D g2d) {
        Ellipse2D e2d = new Ellipse2D.Double(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
        g2d.draw(e2d);
    }

    /**
     * Draw the diagonal cross to the GUI.
     * @param cross the diagonal cross which is being drawn.
     * @param g the graphics instance which is used to paint to the GUI.
     */
    private void drawDiagonalCross(DiagonalCross cross, Graphics g) {
        Line l1 = cross.getFirstLine();
        g.drawLine(l1.getStartX(), l1.getStartY(), l1.getEndX(), l1.getEndY());
        Line l2 = cross.getSecondLine();
        g.drawLine(l2.getStartX(), l2.getStartY(), l2.getEndX(), l2.getEndY());
    }

    /**
     * Paint the shape's border to the GUI in the set colour.
     * @param shape the shape which is being shown on the panel.
     * @param g the graphics instance which is used to paint to the GUI.
     */
    private void paintBorder(Shape shape, Graphics g) {
        SimpleColour colour = shape.getBorderColour(); // get border colour of shape
        g.setColor(new Color(colour.getR(), colour.getG(), colour.getB())); // set the paint colour to be the same as the shapes colour
    }

    /**
     * Paint the shape's fill to the GUI in the set colour.
     * @param shape the shape which is being shown on the panel.
     * @param g the graphics instance which is used to paint to the GUI.
     */
    private void paintFill(Shape shape, Graphics g) {
        SimpleColour fill = shape.getFillColour(); // get fill colour of shape
        g.setColor(new Color(fill.getR(), fill.getG(), fill.getB())); // set the paint colour for the GUI to be the same
    }

    /**
     * Update the model's border colour.
     * The next created shape will have the new border colour.
     * @param c The colour chosen by the user using the JColorChooser.
     */
    public void setBorderColour(Color c) {
        SimpleColour sc = new SimpleColour(c.getRed(), c.getGreen(), c.getBlue());
        dh.setChosenBorderColour(sc);
    }

    /**
     * Update the model's fill colour.
     * The next created shape will have the new fill colour.
     * @param c The colour chosen by the user using the JColorChooser.
     */
    public void setFillColour(Color c) {
        SimpleColour sc = new SimpleColour(c.getRed(), c.getGreen(), c.getBlue());
        dh.setChosenFillColour(sc);
    }

    /**
     * Get the string indicating which button the user selected from the toolbar.
     * @param btn the string for the button.
     */
    public void buttonSelected(String btn) {
        this.buttonSelected = btn;
    }

    /**
     * Clear the drawing panel canvas.
     * Deletes all stores shapes permanently.
     */
    public void clearCanvas() {
        dh.clearShapes(); // delete all stored shapes
        this.removeAll();
        revalidate();
        repaint();
        // set panel visibility to false, when the user clicks on a toolbar button, it will be visible again.
        this.setVisible(false);

    }

    /**
     * Undo the creation of new shapes.
     * Can keep undoing until there are no shapes left on the panel.
     */
    public void undo() {
        // If undo is null then there were no shapes to remove from the panel.
        if (dh.undo() == null) {
            // show dialog box
            JOptionPane.showMessageDialog(this, "No action to undo");
        }
        else {
            repaint();
        }
    }

    /**
     * Redo the deletion of a shape.
     * If shapes were removed using the undo() function then they can be re-added until
     * there are no more shapes to re-add.
     */
    public void redoShape() {
        // If redo is null then there are no more shapes to be re-added to the panel.
        if (dh.redo() == null) {
            // show dialog box
            JOptionPane.showMessageDialog(this, "No action to redo");
        }
        else {
            repaint();
        }
    }


}
