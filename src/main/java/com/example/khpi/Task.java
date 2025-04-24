package com.example.khpi;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Task extends Application
{
    @FXML
    private VBox rightPanel;
    @FXML
    private VBox leftPanel;
    @FXML
    private VBox centerPanel;

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Task.class.getResource("task.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Task");

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onAddGeneratorClick()
    {
        centerPanel.getChildren().clear();

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);

        TextField name_of_generator = new TextField();
        grid.add(new Label("Тип генератора"), 0, 0);
        grid.add(name_of_generator, 1, 0);

        TextField s_nom = new TextField();
        grid.add(new Label("Sном(МВ*А)"), 0, 1);
        grid.add(s_nom, 1, 1);

        TextField p_nom = new TextField();

        centerPanel.getChildren().addAll(grid);
    }

    public static void main(String[] args)
    {
        launch();
    }
}