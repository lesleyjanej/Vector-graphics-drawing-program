# Vector-graphics-drawing-program
A Java Swing application which allows users to draw shapes in different colours and sizes. Source code is located within src folder, within model, main and view packages. main package contains starting point for application, model package contains core logic for the program (Shape class, etc) and view package contains the Java Swing GUI for the application.

The application allows the user to do the following:
 - Draw straight lines
 - Draw rectangles
 - Draw ellipses
 - Draw diagonal crosses
 - Undo for creation of shapes
 - Redo for creation of shapes
 - Different border colours for shape creation
 - Different fill colours for shape creation
 - Clear button to remove all shapes from screen

<b> How to run program: </b> 
Compile all java classes in each java package (model, main and view). One way to do this in Windows 10, is: 

Navigate to src directory and open up cmd. Do the following commands:
javac main/*.java

javac view/*.java

javac model/*.java


Then to run:

java main.DrawingMain
