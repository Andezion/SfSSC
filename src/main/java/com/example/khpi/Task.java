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
        grid.add(new Label("Sном (МВ * А)"), 0, 1);
        grid.add(s_nom, 1, 1);

        TextField p_nom = new TextField();
        grid.add(new Label("Pном (МВт)"), 0, 2);
        grid.add(p_nom, 1, 2);

        TextField cos_f_nom = new TextField();
        grid.add(new Label("cosφном"), 0, 3);
        grid.add(cos_f_nom, 1, 3);

        TextField u_nom = new TextField();
        grid.add(new Label("Uном (кВ)"), 0, 4);
        grid.add(u_nom, 1, 4);

        TextField i_nom = new TextField();
        grid.add(new Label("Iном (кА)"), 0, 5);
        grid.add(i_nom, 1, 5);

        TextField n_nom = new TextField();
        grid.add(new Label("nном (об/мин)"), 0, 6);
        grid.add(n_nom, 1, 6);

        TextField n_ug_n_no = new TextField();
        grid.add(new Label("nуг/nном"), 0, 7);
        grid.add(n_ug_n_no, 1, 7);

        TextField okz = new TextField();
        grid.add(new Label("ОКЗ"), 0, 8);
        grid.add(okz, 1, 8);

        TextField x_dd = new TextField();
        grid.add(new Label("x''d"), 0, 9);
        grid.add(x_dd, 1, 9);

        TextField x_d = new TextField();
        grid.add(new Label("x'd"), 0, 10);
        grid.add(x_d, 1, 10);

        TextField x_d_long = new TextField();
        grid.add(new Label("xd"), 0, 11);
        grid.add(x_d_long, 1, 11);

        TextField x_qq = new TextField();
        grid.add(new Label("x''q"), 0, 12);
        grid.add(x_qq, 1, 12);

        TextField x_q = new TextField();
        grid.add(new Label("xq"), 0, 13);
        grid.add(x_q, 1, 13);

        TextField x2 = new TextField();
        grid.add(new Label("x2"), 0, 14);
        grid.add(x2, 1, 14);

        TextField x0 = new TextField();
        grid.add(new Label("x0"), 0, 15);
        grid.add(x0, 1, 15);

        centerPanel.getChildren().addAll(grid);
    }

    public static void main(String[] args)
    {
        launch();
    }
}