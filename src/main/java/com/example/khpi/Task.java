package com.example.khpi;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    private Label create_error_label(String text)
    {
        Label label = new Label(text);
        label.getStyleClass().add("error_label_generator");
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

        List<TextField> into_database = new ArrayList<>();

        TextField name_of_generator = create_textfield();
        into_database.add(name_of_generator);
        grid.add(create_label("Тип генератора:"), 0, 0);
        grid.add(name_of_generator, 1, 0);
        grid.add(create_helper_label("Впиши название генератора"), 2, 0);
        //grid.add(create_error_label("A - Я"), 3, 0);

        // Параметры
        TextField s_nom = create_textfield();
        grid.add(create_label("Sном (МВ * А):"), 0, 1);
        grid.add(s_nom, 1, 1);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 1);

        TextField p_nom = create_textfield();
        grid.add(create_label("Pном (МВт):"), 0, 2);
        grid.add(p_nom, 1, 2);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 2);

        TextField cos_f_nom = create_textfield();
        grid.add(create_label("cosφном:"), 0, 3);
        grid.add(cos_f_nom, 1, 3);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, 3);

        TextField u_nom = create_textfield();
        grid.add(create_label("Uном (кВ)"), 0, 4);
        grid.add(u_nom, 1, 4);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 4);

        TextField i_nom = create_textfield();
        grid.add(create_label("Iном (кА)"), 0, 5);
        grid.add(i_nom, 1, 5);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 5);

        TextField n_nom = create_textfield();
        grid.add(create_label("nном (об/мин)"), 0, 6);
        grid.add(n_nom, 1, 6);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 6);

        TextField n_ug_n_no = create_textfield();
        grid.add(create_label("nуг/nном"), 0, 7);
        grid.add(n_ug_n_no, 1, 7);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 7);

        TextField okz = create_textfield();
        grid.add(create_label("ОКЗ"), 0, 8);
        grid.add(okz, 1, 8);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 8);

        // Индуктивные сопротивления
        TextField x_dd = create_textfield();
        grid.add(create_label("x''d"), 0, 9);
        grid.add(x_dd, 1, 9);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 9);

        TextField x_d = create_textfield();
        grid.add(create_label("x'd"), 0, 10);
        grid.add(x_d, 1, 10);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 10);

        TextField x_d_long = create_textfield();
        grid.add(create_label("xd"), 0, 11);
        grid.add(x_d_long, 1, 11);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 11);

        TextField x_qq = create_textfield();
        grid.add(create_label("x''q"), 0, 12);
        grid.add(x_qq, 1, 12);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 12);

        TextField x_q = create_textfield();
        grid.add(create_label("xq"), 0, 13);
        grid.add(x_q, 1, 13);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 13);

        TextField x2 = create_textfield();
        grid.add(create_label("x2"), 0, 14);
        grid.add(x2, 1, 14);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 14);

        TextField x0 = create_textfield();
        grid.add(create_label("x0"), 0, 15);
        grid.add(x0, 1, 15);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 15);

        // Время
        TextField t_d0 = create_textfield();
        grid.add(create_label("Td0 (с)"), 0, 16);
        grid.add(t_d0, 1, 16);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 16);

        // Возбуждение
        TextField if_nom = create_textfield();
        grid.add(create_label("Ifном (А)"), 0, 17);
        grid.add(if_nom, 1, 17);
        grid.add(create_helper_label("Впиши целое число"), 2, 17);

        TextField if_nom_ix = create_textfield();
        grid.add(create_label("Ifном/Ix (x)"), 0, 18);
        grid.add(if_nom_ix, 1, 18);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 18);

        TextField uf_nom = create_textfield();
        grid.add(create_label("Ufном (В)"), 0, 19);
        grid.add(uf_nom, 1, 19);
        grid.add(create_helper_label("Впиши целое число"), 2, 19);

        TextField forcing_ratio = create_textfield();
        grid.add(create_label("Кратность\nфорсировки"), 0, 20);
        grid.add(forcing_ratio, 1, 20);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 20);

        TextField excitation_system = create_textfield();
        grid.add(create_label("Система\nвозбуждения"), 0, 21);
        grid.add(excitation_system, 1, 21);
        grid.add(create_helper_label("Впиши название системы"), 2, 21);

        // Коэффициенты
        TextField n_percent = create_textfield();
        grid.add(create_label("ŋ (%)"), 0, 22);
        grid.add(n_percent, 1, 22);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, 22);

        TextField j = create_textfield();
        grid.add(create_label("J (т * m2 * 0.25)"), 0, 23);
        grid.add(j, 1, 23);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 23);

        // Масса
        TextField mass_stator = create_textfield();
        grid.add(create_label("Масса ротора"), 0, 24);
        grid.add(mass_stator, 1, 24);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 24);

        TextField mass_rotor = create_textfield();
        grid.add(create_label("Масса статора"), 0, 25);
        grid.add(mass_rotor, 1, 25);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 25);

        TextField mass_total = create_textfield();
        grid.add(create_label("Масса общая"), 0, 26);
        grid.add(mass_total, 1, 26);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 26);


        // Размеры
        TextField stator_diameter = create_textfield();
        grid.add(create_label("Диаметр статора\nпо корпусу"), 0, 27);
        grid.add(stator_diameter, 1, 27);
        grid.add(create_helper_label("Впиши целое число"), 2, 27);

        TextField rotor_diameter = create_textfield();
        grid.add(create_label("Диаметр ротора"), 0, 28);
        grid.add(rotor_diameter, 1, 28);
        grid.add(create_helper_label("Впиши целое число"), 2, 28);

        TextField generator_length = create_textfield();
        grid.add(create_label("Полная высота\n(длинна) генератора"), 0, 29);
        grid.add(generator_length, 1, 29);
        grid.add(create_helper_label("Впиши целое число"), 2, 29);

        // Параметры какие-то
        TextField execution_type = create_textfield();
        grid.add(create_label("Исполнение"), 0, 30);
        grid.add(execution_type, 1, 30);
        grid.add(create_helper_label("Впиши тип исполнения"), 2, 30);

        TextField exciter_type = create_textfield();
        grid.add(create_label("Тип возбудителя"), 0, 31);
        grid.add(exciter_type, 1, 31);
        grid.add(create_helper_label("Впиши тип возбудителя"), 2, 31);

        TextField turbine = create_textfield();
        grid.add(create_label("Турбина"), 0, 32);
        grid.add(turbine, 1, 32);
        grid.add(create_helper_label("Впиши тип турбины"), 2, 32);

        centerPanel.getChildren().addAll(scroll_pane);
    }

    public static void main(String[] args)
    {
        launch();
    }
}