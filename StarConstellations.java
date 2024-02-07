package codeTemplates;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.JOptionPane;


/**
 * This program creates a star field and makes a patter based on the users input
 * created on Feb 1st, 2023
 *
 *
 * @author Yasser Hasan
 **/

public class StarConstellations extends Application {

    private static final int MAX_X = 800;
    private static final int MAX_Y = 600;

    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(MAX_X, MAX_Y);
        primaryStage.setTitle("Constellation");
        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        scene.setFill(javafx.scene.paint.Color.BLUE);



        Pane pane = new Pane();

        // create star field
        for (int i = 0; i < 500; i++) {
            int x = (int) (Math.random() * MAX_X);
            int y = (int) (Math.random() * MAX_Y);
            Text star = new Text();
            star.setText("*");
            star.setX(x);
            star.setY(y);

            star.setFill(javafx.scene.paint.Color.WHITE);
            pane.getChildren().add(star);
        }

        // dialog to get number of stars
        String numStarsString = JOptionPane.showInputDialog("Enter the number of stars in your constellation:");
        int numStars = Integer.parseInt(numStarsString);

        // dialog to get x and y values for each star
        Circle[] stars = new Circle[numStars];
        for (int i = 0; i < numStars; i++) {
            String xString = JOptionPane.showInputDialog("Enter x value for star " + (i + 1) + ":");
            int x = Integer.parseInt(xString);
            while (x < 0 || x > MAX_X) {
                xString = JOptionPane.showInputDialog("Invalid x value. Enter a value between 0 and " + MAX_X + ":");
                x = Integer.parseInt(xString);
            }

            String yString = JOptionPane.showInputDialog("Enter y value for star " + (i + 1) + ":");
            int y = Integer.parseInt(yString);
            while (y < 0 || y > MAX_Y) {
                yString = JOptionPane.showInputDialog("Invalid y value. Enter a value between 0 and " + MAX_Y + ":");
                y = Integer.parseInt(yString);
            }

            stars[i] = new Circle(x, y, 5);
            pane.getChildren().add(stars[i]);
        }

        // connect stars with lines
        for (int i = 0; i < numStars - 1; i++) {
            Line line = new Line(stars[i].getCenterX(), stars[i].getCenterY(), stars[i + 1].getCenterX(), stars[i + 1].getCenterY());
            pane.getChildren().add(line);
        }

        // connect last star to first
        Line line = new Line(stars[numStars - 1].getCenterX(), stars[numStars - 1].getCenterY(), stars[0].getCenterX(), stars[0].getCenterY());
        pane.getChildren().add(line);

        // get constellation title and display it
        String title = JOptionPane.showInputDialog("Enter a title for your constellation:");
        Text titleText = new Text(20, 20, title);
        pane.getChildren().add(titleText);

        // display programming credits
        Text creditsText = new Text(20, 40, "App created by Yasser Hasan");
        pane.getChildren().add(creditsText);

        // add everything to the root
        root.getChildren().add(pane);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
