package com.example.khpi;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

    private TextField create_textfield()
    {
        TextField tf = new TextField();
        tf.getStyleClass().add("text_field_generator");
        return tf;
    }

    private Label create_label(String text)
    {
        Label label = new Label(text);
        label.getStyleClass().add("label_generator");
        return label;
    }

    private Label create_helper_label(String text)
    {
        Label label = new Label(text);
        label.getStyleClass().add("helper_label_generator");
        return label;
    }

    @FXML
    private void onAddGeneratorClick()
    {
        centerPanel.getChildren().clear();

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        ScrollPane scroll_pane = new ScrollPane(grid);
        scroll_pane.setId("my-scroll");

        TextField name_of_generator = create_textfield();
        grid.add(create_label("Тип генератора:"), 0, 0);
        grid.add(name_of_generator, 1, 0);
        grid.add(create_helper_label("Впиши название генератора"), 2, 0);

        // Параметры
        TextField s_nom = create_textfield();
        grid.add(new Label("Sном (МВ * А)"), 0, 1);
        grid.add(s_nom, 1, 1);
        grid.add(new Label("Впиши число, если дробное то используй - \".\""), 2, 1);

        TextField p_nom = create_textfield();
        grid.add(new Label("Pном (МВт)"), 0, 2);
        grid.add(p_nom, 1, 2);

        TextField cos_f_nom = create_textfield();
        grid.add(new Label("cosφном"), 0, 3);
        grid.add(cos_f_nom, 1, 3);

        TextField u_nom = create_textfield();
        grid.add(new Label("Uном (кВ)"), 0, 4);
        grid.add(u_nom, 1, 4);

        TextField i_nom = create_textfield();
        grid.add(new Label("Iном (кА)"), 0, 5);
        grid.add(i_nom, 1, 5);

        TextField n_nom = create_textfield();
        grid.add(new Label("nном (об/мин)"), 0, 6);
        grid.add(n_nom, 1, 6);

        TextField n_ug_n_no = create_textfield();
        grid.add(new Label("nуг/nном"), 0, 7);
        grid.add(n_ug_n_no, 1, 7);

        TextField okz = create_textfield();
        grid.add(new Label("ОКЗ"), 0, 8);
        grid.add(okz, 1, 8);

        // Индуктивные сопротивления
        TextField x_dd = create_textfield();
        grid.add(new Label("x''d"), 0, 9);
        grid.add(x_dd, 1, 9);

        TextField x_d = create_textfield();
        grid.add(new Label("x'd"), 0, 10);
        grid.add(x_d, 1, 10);

        TextField x_d_long = create_textfield();
        grid.add(new Label("xd"), 0, 11);
        grid.add(x_d_long, 1, 11);

        TextField x_qq = create_textfield();
        grid.add(new Label("x''q"), 0, 12);
        grid.add(x_qq, 1, 12);

        TextField x_q = create_textfield();
        grid.add(new Label("xq"), 0, 13);
        grid.add(x_q, 1, 13);

        TextField x2 = create_textfield();
        grid.add(new Label("x2"), 0, 14);
        grid.add(x2, 1, 14);

        TextField x0 = create_textfield();
        grid.add(new Label("x0"), 0, 15);
        grid.add(x0, 1, 15);

        // Время
        TextField t_d0 = create_textfield();
        grid.add(new Label("Td0 (с)"), 0, 16);
        grid.add(t_d0, 1, 16);

        // Возбуждение
        TextField if_nom = create_textfield();
        grid.add(new Label("Ifном (А)"), 0, 17);
        grid.add(if_nom, 1, 17);

        TextField if_nom_ix = create_textfield();
        grid.add(new Label("Ifном/Ix (x)"), 0, 18);
        grid.add(if_nom_ix, 1, 18);

        TextField uf_nom = create_textfield();
        grid.add(new Label("Ufном (В)"), 0, 19);
        grid.add(uf_nom, 1, 19);

        TextField forcing_ratio = create_textfield();
        grid.add(new Label("Кратность\nфорсировки"), 0, 20);
        grid.add(forcing_ratio, 1, 20);

        TextField excitation_system = create_textfield();
        grid.add(new Label("Система\nвозбуждения"), 0, 21);
        grid.add(excitation_system, 1, 21);

        // Коэффициенты
        TextField n_percent = create_textfield();
        grid.add(new Label("ŋ (%)"), 0, 22);
        grid.add(n_percent, 1, 22);

        TextField j = create_textfield();
        grid.add(new Label("J (т * m2 * 0.25)"), 0, 23);
        grid.add(j, 1, 23);

        // Масса
        TextField mass_stator = create_textfield();
        grid.add(new Label("Масса ротора"), 0, 24);
        grid.add(mass_stator, 1, 24);

        TextField mass_rotor = create_textfield();
        grid.add(new Label("Масса статора"), 0, 25);
        grid.add(mass_rotor, 1, 25);

        TextField mass_total = create_textfield();
        grid.add(new Label("Масса общая"), 0, 26);
        grid.add(mass_total, 1, 26);


        // Размеры
        TextField stator_diameter = create_textfield();
        grid.add(new Label("Диаметр статора\nпо корпусу"), 0, 27);
        grid.add(stator_diameter, 1, 27);

        TextField rotor_diameter = create_textfield();
        grid.add(new Label("Диаметр ротора"), 0, 28);
        grid.add(rotor_diameter, 1, 28);

        TextField generator_length = create_textfield();
        grid.add(new Label("Полная высота\n(длинна) генератора"), 0, 29);
        grid.add(generator_length, 1, 29);

        // Параметры какие-то
        TextField execution_type = create_textfield();
        grid.add(new Label("Исполнение"), 0, 30);
        grid.add(execution_type, 1, 30);

        TextField exciter_type = create_textfield();
        grid.add(new Label("Тип возбудителя"), 0, 31);
        grid.add(exciter_type, 1, 31);

        TextField turbine = create_textfield();
        grid.add(new Label("Турбина"), 0, 32);
        grid.add(turbine, 1, 32);

        centerPanel.getChildren().addAll(scroll_pane);
    }

    public static void main(String[] args)
    {
        launch();
    }
}