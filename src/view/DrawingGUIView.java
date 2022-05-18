package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class uses Swing to display content from the model to the user through a
 * graphical user interface.
 * The class listens to the user's actions using an ActionListener, and calls the
 * DrawingPanel class when a user selects a shape to draw. Changes are made to the model
 * based on the user's actions.
 *
 * @author Lesleyjane J https://github.com/lesleyjanej
 */
public class DrawingGUIView implements ActionListener {
    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 600;

    private JFrame mainFrame;
    private DrawingPanel panel; // the panel which the user may draw shapes on
    private JScrollPane drawingPane;
    private JToolBar toolbar;

    // Toolbar buttons
    private JButton undoBtn;
    private JButton redoBtn;
    private JButton colourBtn;
    private JButton fillBtn;
    private JButton lineBtn;
    private JButton rectangleBtn;
    private JButton ellipsesBtn;
    private JButton diagonalCrossBtn;
    private JButton clearBtn;

    /**
     * Create a new DrawingGUIView.
     */
    public DrawingGUIView() {
        this.mainFrame = new JFrame();
        toolbar = new JToolBar();
        panel = new DrawingPanel();
        panel.setVisible(false); // initially set to invisible until the user clicks on a shape to draw.
        drawingPane = new JScrollPane(panel); // scrolling pane is on the panel
        setupComponents();
    }

    /**
     * Set up the JFrame by adding the toolbar and configuring the frame.
     */
    private void setupComponents() {
        setupToolbar();
        mainFrame.add(drawingPane, BorderLayout.CENTER);
        mainFrame.setSize (FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initialise and add the buttons to the toolbar.
     * Actionlisteners for each button are added.
     */
    private void setupToolbar(){
        // Initialise all buttons for toolbar
        undoBtn = new JButton("Undo");
        redoBtn = new JButton("Redo");
        colourBtn = new JButton("Colour");
        fillBtn = new JButton("Fill");
        lineBtn = new JButton("Line");
        rectangleBtn = new JButton("Rectangle");
        ellipsesBtn = new JButton("Ellipses");
        diagonalCrossBtn = new JButton("Cross");
        clearBtn = new JButton("Clear");
        // add action listeners for each button
        addActionListenerForButtons(this);
        // add buttons to the toolbar
        toolbar.add(undoBtn);
        toolbar.add(redoBtn);
        toolbar.add(colourBtn);
        toolbar.add(fillBtn);
        toolbar.add(lineBtn);
        toolbar.add(rectangleBtn);
        toolbar.add(ellipsesBtn);
        toolbar.add(diagonalCrossBtn);
        toolbar.add(clearBtn);
        // add toolbar to north of main frame
        mainFrame.add(toolbar, BorderLayout.NORTH);
    }

    /**
     * Add action listeners to the buttons in the toolbar.
     * The actionPerformed() method will handle the events.
     * @param al an Actionlistener.
     */
    public void addActionListenerForButtons(ActionListener al) {
        undoBtn.addActionListener(al);
        redoBtn.addActionListener(al);
        colourBtn.addActionListener(al);
        fillBtn.addActionListener(al);
        lineBtn.addActionListener(al);
        rectangleBtn.addActionListener(al);
        ellipsesBtn.addActionListener(al);
        diagonalCrossBtn.addActionListener(al);
        clearBtn.addActionListener(al);
    }

    /**
     * When a button in the toolbar is clicked, the actionListener for that button
     * will call this method to find which action it should take.
     * For each button in the toolbar, a DrawingPanel method is called.
     * The DrawingPanel methods will then update and access the model.
     * @param e actionEvent.
     */
    public void actionPerformed(ActionEvent e) {
        // set drawing panel to be visible
        panel.setVisible(true);
        // Check to see which button is pressed, and call appropriate panel methods
        if (e.getSource() == undoBtn) {
            panel.undo();
        }
        else if (e.getSource() == redoBtn) {
            panel.redoShape();
        }
        else if (e.getSource() == colourBtn) {
            // Show a pop-up window for choosing the colour
            Color color = JColorChooser.showDialog(mainFrame,"Select a color", Color.BLACK);
            // If the user did not choose a colour, set the colour to black
            if (color == null) {
                color = Color.BLACK;
            }
            panel.setBorderColour(color);
            panel.buttonSelected("colourBtn");
        }
        else if (e.getSource() == fillBtn) {
            // Show a pop-up window for choosing the colour
            Color color = JColorChooser.showDialog(mainFrame,"Select a fill color",Color.BLACK);
            // If the user did not choose a colour, set the colour to black
            if (color == null) {
                color = Color.BLACK;
            }
            panel.setFillColour(color);
            panel.buttonSelected("fillBtn");
        }
        else if (e.getSource() == lineBtn) {
            panel.buttonSelected("lineBtn");
        }
        else if (e.getSource() == rectangleBtn) {
            panel.buttonSelected("rectangleBtn");
        }
        else if (e.getSource() == ellipsesBtn) {
            panel.buttonSelected("ellipseBtn");
        }
        else if (e.getSource() == diagonalCrossBtn) {
            panel.buttonSelected("diagonalCrossBtn");
        }
        else if (e.getSource() == clearBtn) {
            panel.buttonSelected("clearBtn");
            panel.clearCanvas();
        }
    }

}
