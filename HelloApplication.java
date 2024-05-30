package com.example.elasticcollision;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IntersectingButtons extends Application {

    private Button button1;
    private Button button2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Создаем панель для размещения кнопок
        Pane root = new Pane();

        // Создаем две кнопки
        button1 = new Button("Кнопка 1");
        button2 = new Button("Кнопка 2");

        // Устанавливаем начальные позиции кнопок
        button1.setLayoutX(50);
        button1.setLayoutY(50);
        button2.setLayoutX(100);
        button2.setLayoutY(100);

        // Добавляем обработчики событий для перемещения кнопок
        button1.setOnMouseDragged(e -> {
            button1.setLayoutX(e.getSceneX() - button1.getWidth() / 2);
            button1.setLayoutY(e.getSceneY() - button1.getHeight() / 2);
            calculateIntersection();
        });

        button2.setOnMouseDragged(e -> {
            button2.setLayoutX(e.getSceneX() - button2.getWidth() / 2);
            button2.setLayoutY(e.getSceneY() - button2.getHeight() / 2);
            calculateIntersection();
        });

        // Добавляем кнопки на панель
        root.getChildren().addAll(button1, button2);

        // Создаем сцену и устанавливаем ее на стадию
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Пересекающиеся кнопки");
        primaryStage.show();
    }

    // Метод для вычисления площади пересечения кнопок
    private void calculateIntersection() {
        // Получаем координаты и размеры кнопок
        double x1 = button1.getLayoutX();
        double y1 = button1.getLayoutY();
        double width1 = button1.getWidth();
        double height1 = button1.getHeight();

        double x2 = button2.getLayoutX();
        double y2 = button2.getLayoutY();
        double width2 = button2.getWidth();
        double height2 = button2.getHeight();

        // Вычисляем пересечение
        double intersectionWidth = Math.min(x1 + width1, x2 + width2) - Math.max(x1, x2);
        double intersectionHeight = Math.min(y1 + height1, y2 + height2) - Math.max(y1, y2);

        // Проверяем, пересекаются ли кнопки
        if (intersectionWidth > 0 && intersectionHeight > 0) {
            // Вычисляем площадь пересечения
            double intersectionArea = intersectionWidth * intersectionHeight;
            System.out.println("Площадь пересечения: " + intersectionArea);
        } else {
            System.out.println("Кнопки не пересекаются");
        }
    }
}
