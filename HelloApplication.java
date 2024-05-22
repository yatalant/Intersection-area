package com.example.elasticcollision;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloApplication extends Application {
    private Button button1;
    private Button button2;
    private Rectangle intersectionButtons;
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);

        button1 = new Button("Button 1");
        button1.setMinSize(100,100);
        button1.setLayoutX(200);
        button1.setLayoutY(100);

        button2 = new Button("Button 2");
        button2.setMinSize(100,100);
        button2.setLayoutX(200);
        button2.setLayoutY(300);

        intersectionButtons = new Rectangle();
        intersectionButtons.setFill(Color.HOTPINK);
        intersectionButtons.setVisible(false);

        pane.getChildren().addAll(button1,button2,intersectionButtons);
        ButtonCoords buttonCoords = new ButtonCoords();

        button1.setOnMousePressed(mouseEvent -> {
            buttonCoords.setX(mouseEvent.getSceneX() - button1.getLayoutX());
            buttonCoords.setY(mouseEvent.getSceneY() - button1.getLayoutY());

        });

        button1.setOnMouseDragged(mouseEvent -> {
            button1.setLayoutX(mouseEvent.getSceneX() - buttonCoords.getX());
            button1.setLayoutY(mouseEvent.getSceneY() - buttonCoords.getY());
            intersectionCheck();
        });

        button2.setOnMousePressed(mouseEvent -> {
            buttonCoords.setX(mouseEvent.getSceneX() - button2.getLayoutX());
            buttonCoords.setY(mouseEvent.getSceneY() - button2.getLayoutY());
        });

        button2.setOnMouseDragged(mouseEvent -> {
            button2.setLayoutX(mouseEvent.getSceneX() - buttonCoords.getX());
            button2.setLayoutY(mouseEvent.getSceneY() - buttonCoords.getY());
            intersectionCheck();
        });

        stage.setTitle("Intersection");
        stage.setScene(scene);
        stage.show();
    }
    private void intersectionCheck() {
        intersectionButtons.setVisible(button1.getBoundsInParent().intersects(button2.getBoundsInParent()));
        if (intersectionButtons.isVisible()) {
            double intersectWidth = Math.min(button1.getBoundsInParent().getMaxX(), button2.getBoundsInParent().getMaxX()) -
                    Math.max(button1.getBoundsInParent().getMinX(), button2.getBoundsInParent().getMinX());

            double intersectHeight = Math.min(button1.getBoundsInParent().getMaxY(), button2.getBoundsInParent().getMaxY()) -
                    Math.max(button1.getBoundsInParent().getMinY(), button2.getBoundsInParent().getMinY());

            intersectionButtons.setX(Math.max(button1.getBoundsInParent().getMinX(), button2.getBoundsInParent().getMinX()));
            intersectionButtons.setY(Math.max(button1.getBoundsInParent().getMinY(), button2.getBoundsInParent().getMinY()));

            intersectionButtons.setWidth(intersectWidth);
            intersectionButtons.setHeight(intersectHeight);

            double area = intersectWidth * intersectHeight;
            System.out.println("Intersection area: " + area);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}