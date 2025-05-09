package com.example.khpi;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private void onAddTransformatorClick()
     {
        centerPanel.getChildren().clear();

        int helper = 0;

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        ScrollPane scroll_pane = new ScrollPane(grid);
        scroll_pane.setId("my-scroll");

        List<TextField> into_database = new ArrayList<>();

        grid.add(create_helper_label("Трансформатор"), 0, helper);
        helper++;

        TextField name_of_transformator = create_textfield();
        into_database.add(name_of_transformator);
        grid.add(create_label("Тип\nтрансформатора:"),0 ,helper);
        grid.add(name_of_transformator,1 ,helper);
        grid.add(create_helper_label("Впиши название трансформатора"), 2, helper);

        helper++;

        // Параметры
        TextField s_nom = create_textfield();
        into_database.add(s_nom);
        grid.add(create_label("Sном (МВ * А):"), 0, helper);
        grid.add(s_nom,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField s_nn = create_textfield();
        into_database.add(s_nn);
        grid.add(create_label("Sнн (МВ * А):"), 0, helper);
        grid.add(s_nn,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Напряжение обмотки
        TextField bn = create_textfield();
        into_database.add(bn);
        grid.add(create_label("Напряжение BH\n(кВ):"), 0, helper);
        grid.add(bn,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField cn = create_textfield();
        into_database.add(cn);
        grid.add(create_label("Напряжение CH\n(кВ):"), 0, helper);
        grid.add(cn,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField hh = create_textfield();
        into_database.add(hh);
        grid.add(create_label("Напряжение HH\n(кВ):"), 0, helper);
        grid.add(hh,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Потери
        TextField pxxA = create_textfield();
        into_database.add(pxxA);
        grid.add(create_label("Потери PxX-A\n(кВт):"), 0, helper);
        grid.add(pxxA,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField pxxB = create_textfield();
        into_database.add(pxxB);
        grid.add(create_label("Потери PxX-B\n(кВт):"), 0, helper);
        grid.add(pxxB,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField bhch = create_textfield();
        into_database.add(bhch);
        grid.add(create_label("Потери Pк.з\nBH-CH (кВт):"), 0, helper);
        grid.add(bhch,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField bhhh = create_textfield();
        into_database.add(bhhh);
        grid.add(create_label("Потери Pк.з\nBH-HH (кВт):"), 0, helper);
        grid.add(bhhh,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField chhh = create_textfield();
        into_database.add(chhh);
        grid.add(create_label("Потери Pк.з\nCH-HH (кВт):"), 0, helper);
        grid.add(chhh,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField Ubhch = create_textfield();
        into_database.add(Ubhch);
        grid.add(create_label("Uк BH-CH (%):"), 0, helper);
        grid.add(Ubhch,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField Ubhhh = create_textfield();
        into_database.add(Ubhhh);
        grid.add(create_label("Uк BH-HH (%):"), 0, helper);
        grid.add(Ubhhh,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField Uchhh = create_textfield();
        into_database.add(Uchhh);
        grid.add(create_label("Uк CH-HH (%):"), 0, helper);
        grid.add(Uchhh,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField Ixx = create_textfield();
        into_database.add(Ixx);
        grid.add(create_label("Ix.x (%):"), 0, helper);
        grid.add(Ixx,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        // Габариты
        TextField dlina = create_textfield();
        into_database.add(dlina);
        grid.add(create_label("Длина (м):"), 0, helper);
        grid.add(dlina,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField shirina = create_textfield();
        into_database.add(shirina);
        grid.add(create_label("Ширина (м):"), 0, helper);
        grid.add(shirina,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField vysota_do = create_textfield();
        into_database.add(vysota_do);
        grid.add(create_label("Высота до\nкрышки (м):"), 0, helper);
        grid.add(vysota_do,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField vysota_posle = create_textfield();
        into_database.add(vysota_posle);
        grid.add(create_label("Высота полная\n(м):"), 0, helper);
        grid.add(vysota_posle,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        // Масса
        TextField maslo = create_textfield();
        into_database.add(maslo);
        grid.add(create_label("Масса масла (т):"), 0, helper);
        grid.add(maslo,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField transport = create_textfield();
        into_database.add(transport);
        grid.add(create_label("Масла\nтранспортная (м):"), 0, helper);
        grid.add(transport,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField polna = create_textfield();
        into_database.add(polna);
        grid.add(create_label("Масса полная (м):"), 0, helper);
        grid.add(polna,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField suma = create_textfield();
        into_database.add(suma);
        grid.add(create_label("Цена (грн):"), 0, helper);
        grid.add(suma,1 ,helper);
        grid.add(create_helper_label("Впиши цену"), 2, helper);

        helper++;

        Button save_to_database = new Button("Сохранить");
        save_to_database.setId("save_gen");
        save_to_database.setOnAction(e -> save_transformator_to_database(into_database));
        grid.add(save_to_database, 2, helper);

        centerPanel.getChildren().addAll(scroll_pane);
    }

    @FXML
    private void onAddAutoTransClick()
    {
        centerPanel.getChildren().clear();

        int helper = 0;

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        ScrollPane scroll_pane = new ScrollPane(grid);
        scroll_pane.setId("my-scroll");

        List<TextField> into_database = new ArrayList<>();

        grid.add(create_helper_label("Автотрансформатор"), 0, helper);
        helper++;

        TextField name_of_transformator = create_textfield();
        into_database.add(name_of_transformator);
        grid.add(create_label("Тип авто-\nтрансформатора:"),0 ,helper);
        grid.add(name_of_transformator,1 ,helper);
        grid.add(create_helper_label("Впиши название автотрансформатора"), 2, helper);

        helper++;

        // Параметры
        TextField s_nom = create_textfield();
        into_database.add(s_nom);
        grid.add(create_label("Sном (МВ * А):"), 0, helper);
        grid.add(s_nom,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField s_nn = create_textfield();
        into_database.add(s_nn);
        grid.add(create_label("Sнн (МВ * А):"), 0, helper);
        grid.add(s_nn,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Напряжение обмотки
        TextField bn = create_textfield();
        into_database.add(bn);
        grid.add(create_label("Напряжение BH\n(кВ):"), 0, helper);
        grid.add(bn,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField cn = create_textfield();
        into_database.add(cn);
        grid.add(create_label("Напряжение CH\n(кВ):"), 0, helper);
        grid.add(cn,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField hh = create_textfield();
        into_database.add(hh);
        grid.add(create_label("Напряжение HH\n(кВ):"), 0, helper);
        grid.add(hh,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Потери
        TextField pxxA = create_textfield();
        into_database.add(pxxA);
        grid.add(create_label("Потери PxX-A\n(кВт):"), 0, helper);
        grid.add(pxxA,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField pxxB = create_textfield();
        into_database.add(pxxB);
        grid.add(create_label("Потери PxX-B\n(кВт):"), 0, helper);
        grid.add(pxxB,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField bhch = create_textfield();
        into_database.add(bhch);
        grid.add(create_label("Потери Pк.з\nBH-CH (кВт):"), 0, helper);
        grid.add(bhch,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField bhhh = create_textfield();
        into_database.add(bhhh);
        grid.add(create_label("Потери Pк.з\nBH-HH (кВт):"), 0, helper);
        grid.add(bhhh,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField chhh = create_textfield();
        into_database.add(chhh);
        grid.add(create_label("Потери Pк.з\nCH-HH (кВт):"), 0, helper);
        grid.add(chhh,1 ,helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField Ubhch = create_textfield();
        into_database.add(Ubhch);
        grid.add(create_label("Uк BH-CH (%):"), 0, helper);
        grid.add(Ubhch,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField Ubhhh = create_textfield();
        into_database.add(Ubhhh);
        grid.add(create_label("Uк BH-HH (%):"), 0, helper);
        grid.add(Ubhhh,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField Uchhh = create_textfield();
        into_database.add(Uchhh);
        grid.add(create_label("Uк CH-HH (%):"), 0, helper);
        grid.add(Uchhh,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField Ixx = create_textfield();
        into_database.add(Ixx);
        grid.add(create_label("Ix.x (%):"), 0, helper);
        grid.add(Ixx,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        // Габариты
        TextField dlina = create_textfield();
        into_database.add(dlina);
        grid.add(create_label("Длина (м):"), 0, helper);
        grid.add(dlina,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField shirina = create_textfield();
        into_database.add(shirina);
        grid.add(create_label("Ширина (м):"), 0, helper);
        grid.add(shirina,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField vysota_do = create_textfield();
        into_database.add(vysota_do);
        grid.add(create_label("Высота до\nкрышки (м):"), 0, helper);
        grid.add(vysota_do,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField vysota_posle = create_textfield();
        into_database.add(vysota_posle);
        grid.add(create_label("Высота полная\n(м):"), 0, helper);
        grid.add(vysota_posle,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        // Масса
        TextField maslo = create_textfield();
        into_database.add(maslo);
        grid.add(create_label("Масса масла (т):"), 0, helper);
        grid.add(maslo,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField transport = create_textfield();
        into_database.add(transport);
        grid.add(create_label("Масла\nтранспортная (м):"), 0, helper);
        grid.add(transport,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField polna = create_textfield();
        into_database.add(polna);
        grid.add(create_label("Масса полная (м):"), 0, helper);
        grid.add(polna,1 ,helper);
        grid.add(create_helper_label("Впиши число больше 0"), 2, helper);

        helper++;

        TextField suma = create_textfield();
        into_database.add(suma);
        grid.add(create_label("Цена (грн):"), 0, helper);
        grid.add(suma,1 ,helper);
        grid.add(create_helper_label("Впиши цену"), 2, helper);

        helper++;

        Button save_to_database = new Button("Сохранить");
        save_to_database.setId("save_gen");
        save_to_database.setOnAction(e -> save_autotransformator_to_database(into_database));
        grid.add(save_to_database, 2, helper);

        centerPanel.getChildren().addAll(scroll_pane);
    }

    @FXML
    private void onAddGeneratorClick()
     {
        centerPanel.getChildren().clear();

        int helper = 0;

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        ScrollPane scroll_pane = new ScrollPane(grid);
        scroll_pane.setId("my-scroll");

        List<TextField> into_database = new ArrayList<>();

        grid.add(create_helper_label("Генераторы для ТЭС"), 0, helper);
        helper++;

        TextField name_of_generator = create_textfield();
        into_database.add(name_of_generator);
        grid.add(create_label("Тип генератора:"), 0, helper);
        grid.add(name_of_generator, 1, helper);
        grid.add(create_helper_label("Впиши название генератора"), 2, helper);

        helper++;

        // Параметры
        TextField s_nom = create_textfield();
        into_database.add(s_nom);
        grid.add(create_label("Sном (МВ * А):"), 0, helper);
        grid.add(s_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField p_nom = create_textfield();
        into_database.add(p_nom);
        grid.add(create_label("Pном (МВт):"), 0, helper);
        grid.add(p_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField cos_f_nom = create_textfield();
        into_database.add(cos_f_nom);
        grid.add(create_label("cosφном (%):"), 0, helper);
        grid.add(cos_f_nom, 1, helper);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, helper);

        helper++;

        TextField u_nom = create_textfield();
        into_database.add(u_nom);
        grid.add(create_label("Uном (кВ):"), 0, helper);
        grid.add(u_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField i_nom = create_textfield();
        into_database.add(i_nom);
        grid.add(create_label("Iном (кА):"), 0, helper);
        grid.add(i_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField n_nom = create_textfield();
        into_database.add(n_nom);
        grid.add(create_label("nном (об/мин):"), 0, helper);
        grid.add(n_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField n_ug_n_no = create_textfield();
        into_database.add(n_ug_n_no);
        grid.add(create_label("nуг/nном:"), 0, helper);
        grid.add(n_ug_n_no, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField okz = create_textfield();
        into_database.add(okz);
        grid.add(create_label("ОКЗ:"), 0, helper);
        grid.add(okz, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Индуктивные сопротивления
        TextField x_dd = create_textfield();
        into_database.add(x_dd);
        grid.add(create_label("x''d:"), 0, helper);
        grid.add(x_dd, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x_d = create_textfield();
        into_database.add(x_d);
        grid.add(create_label("x'd:"), 0, helper);
        grid.add(x_d, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x_d_long = create_textfield();
        into_database.add(x_d_long);
        grid.add(create_label("xd:"), 0, helper);
        grid.add(x_d_long, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x_qq = create_textfield();
        into_database.add(x_qq);
        grid.add(create_label("x''q:"), 0, helper);
        grid.add(x_qq, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x_q = create_textfield();
        into_database.add(x_q);
        grid.add(create_label("xq:"), 0, helper);
        grid.add(x_q, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x2 = create_textfield();
        into_database.add(x2);
        grid.add(create_label("x2:"), 0, helper);
        grid.add(x2, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x0 = create_textfield();
        into_database.add(x0);
        grid.add(create_label("x0:"), 0, helper);
        grid.add(x0, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Время
        TextField t_d0 = create_textfield();
        into_database.add(t_d0);
        grid.add(create_label("Td0 (с):"), 0, helper);
        grid.add(t_d0, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Возбуждение
        TextField if_nom = create_textfield();
        into_database.add(if_nom);
        grid.add(create_label("Ifном (А):"), 0, helper);
        grid.add(if_nom, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        TextField if_nom_ix = create_textfield();
        into_database.add(if_nom_ix);
        grid.add(create_label("Ifном/Ix (x):"), 0, helper);
        grid.add(if_nom_ix, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField uf_nom = create_textfield();
        into_database.add(uf_nom);
        grid.add(create_label("Ufном (В):"), 0, helper);
        grid.add(uf_nom, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        TextField forcing_ratio = create_textfield();
        into_database.add(forcing_ratio);
        grid.add(create_label("Кратность\nфорсировки:"), 0, helper);
        grid.add(forcing_ratio, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField excitation_system = create_textfield();
        into_database.add(excitation_system);
        grid.add(create_label("Система\nвозбуждения:"), 0, helper);
        grid.add(excitation_system, 1, helper);
        grid.add(create_helper_label("Впиши название системы"), 2, helper);

        helper++;

        // Коэффициенты
        TextField n_percent = create_textfield();
        into_database.add(n_percent);
        grid.add(create_label("ŋ (%):"), 0, helper);
        grid.add(n_percent, 1, helper);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, helper);

        helper++;

        TextField j = create_textfield();
        into_database.add(j);
        grid.add(create_label("J (т * m2 * 0.25):"), 0, helper);
        grid.add(j, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Масса
        TextField mass_stator = create_textfield();
        into_database.add(mass_stator);
        grid.add(create_label("Масса ротора (т):"), 0, helper);
        grid.add(mass_stator, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField mass_rotor = create_textfield();
        into_database.add(mass_rotor);
        grid.add(create_label("Масса статора (т):"), 0, helper);
        grid.add(mass_rotor, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField mass_total = create_textfield();
        into_database.add(mass_total);
        grid.add(create_label("Масса общая (т):"), 0, helper);
        grid.add(mass_total, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Размеры
        TextField stator_diameter = create_textfield();
        into_database.add(stator_diameter);
        grid.add(create_label("Диаметр статора\nпо корпусу (мм):"), 0, helper);
        grid.add(stator_diameter, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        TextField rotor_diameter = create_textfield();
        into_database.add(rotor_diameter);
        grid.add(create_label("Диаметр ротора\n(мм):"), 0, helper);
        grid.add(rotor_diameter, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        TextField generator_length = create_textfield();
        into_database.add(generator_length);
        grid.add(create_label("Полная высота\n(длинна) генер-\nатора (мм):"), 0, helper);
        grid.add(generator_length, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        // Параметры какие-то
        TextField execution_type = create_textfield();
        into_database.add(execution_type);
        grid.add(create_label("Исполнение:"), 0, helper);
        grid.add(execution_type, 1, helper);
        grid.add(create_helper_label("Впиши тип исполнения"), 2, helper);

        helper++;

        TextField exciter_type = create_textfield();
        into_database.add(exciter_type);
        grid.add(create_label("Тип возбудителя:"), 0, helper);
        grid.add(exciter_type, 1, helper);
        grid.add(create_helper_label("Впиши тип возбудителя"), 2, helper);

        helper++;

        TextField turbine = create_textfield();
        into_database.add(turbine);
        grid.add(create_label("Турбина:"), 0, helper);
        grid.add(turbine, 1, helper);
        grid.add(create_helper_label("Впиши тип турбины"), 2, helper);

        helper++;

        Button save_to_database = new Button("Сохранить");
        save_to_database.setId("save_gen");
        save_to_database.setOnAction(e -> save_generator_to_database(into_database));
        grid.add(save_to_database, 3, helper);

        centerPanel.getChildren().addAll(scroll_pane);
    }

    @FXML
    private void onAddGeneratorClick1()
    {
        centerPanel.getChildren().clear();

        int helper = 0;

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        ScrollPane scroll_pane = new ScrollPane(grid);
        scroll_pane.setId("my-scroll");

        List<TextField> into_database = new ArrayList<>();

        grid.add(create_helper_label("Генераторы для ГЭС"), 0, helper);
        helper++;

        TextField name_of_generator = create_textfield();
        into_database.add(name_of_generator);
        grid.add(create_label("Тип генератора:"), 0, helper);
        grid.add(name_of_generator, 1, helper);
        grid.add(create_helper_label("Впиши название генератора"), 2, helper);

        helper++;

        // Параметры
        TextField s_nom = create_textfield();
        into_database.add(s_nom);
        grid.add(create_label("Sном (МВ * А):"), 0, helper);
        grid.add(s_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField p_nom = create_textfield();
        into_database.add(p_nom);
        grid.add(create_label("Pном (МВт):"), 0, helper);
        grid.add(p_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField cos_f_nom = create_textfield();
        into_database.add(cos_f_nom);
        grid.add(create_label("cosφном (%):"), 0, helper);
        grid.add(cos_f_nom, 1, helper);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, helper);

        helper++;

        TextField u_nom = create_textfield();
        into_database.add(u_nom);
        grid.add(create_label("Uном (кВ):"), 0, helper);
        grid.add(u_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField i_nom = create_textfield();
        into_database.add(i_nom);
        grid.add(create_label("Iном (кА):"), 0, helper);
        grid.add(i_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField n_nom = create_textfield();
        into_database.add(n_nom);
        grid.add(create_label("nном (об/мин):"), 0, helper);
        grid.add(n_nom, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField n_ug_n_no = create_textfield();
        into_database.add(n_ug_n_no);
        grid.add(create_label("nуг/nном:"), 0, helper);
        grid.add(n_ug_n_no, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField okz = create_textfield();
        into_database.add(okz);
        grid.add(create_label("ОКЗ:"), 0, helper);
        grid.add(okz, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Индуктивные сопротивления
        TextField x_dd = create_textfield();
        into_database.add(x_dd);
        grid.add(create_label("x''d:"), 0, helper);
        grid.add(x_dd, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x_d = create_textfield();
        into_database.add(x_d);
        grid.add(create_label("x'd:"), 0, helper);
        grid.add(x_d, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x_d_long = create_textfield();
        into_database.add(x_d_long);
        grid.add(create_label("xd:"), 0, helper);
        grid.add(x_d_long, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x_qq = create_textfield();
        into_database.add(x_qq);
        grid.add(create_label("x''q:"), 0, helper);
        grid.add(x_qq, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x_q = create_textfield();
        into_database.add(x_q);
        grid.add(create_label("xq:"), 0, helper);
        grid.add(x_q, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x2 = create_textfield();
        into_database.add(x2);
        grid.add(create_label("x2:"), 0, helper);
        grid.add(x2, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField x0 = create_textfield();
        into_database.add(x0);
        grid.add(create_label("x0:"), 0, helper);
        grid.add(x0, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Время
        TextField t_d0 = create_textfield();
        into_database.add(t_d0);
        grid.add(create_label("Td0 (с):"), 0, helper);
        grid.add(t_d0, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Возбуждение
        TextField if_nom = create_textfield();
        into_database.add(if_nom);
        grid.add(create_label("Ifном (А):"), 0, helper);
        grid.add(if_nom, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        TextField if_nom_ix = create_textfield();
        into_database.add(if_nom_ix);
        grid.add(create_label("Ifном/Ix (x):"), 0, helper);
        grid.add(if_nom_ix, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField uf_nom = create_textfield();
        into_database.add(uf_nom);
        grid.add(create_label("Ufном (В):"), 0, helper);
        grid.add(uf_nom, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        TextField forcing_ratio = create_textfield();
        into_database.add(forcing_ratio);
        grid.add(create_label("Кратность\nфорсировки:"), 0, helper);
        grid.add(forcing_ratio, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField excitation_system = create_textfield();
        into_database.add(excitation_system);
        grid.add(create_label("Система\nвозбуждения:"), 0, helper);
        grid.add(excitation_system, 1, helper);
        grid.add(create_helper_label("Впиши название системы"), 2, helper);

        helper++;

        // Коэффициенты
        TextField n_percent = create_textfield();
        into_database.add(n_percent);
        grid.add(create_label("ŋ (%):"), 0, helper);
        grid.add(n_percent, 1, helper);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, helper);

        helper++;

        TextField j = create_textfield();
        into_database.add(j);
        grid.add(create_label("J (т * m2 * 0.25):"), 0, helper);
        grid.add(j, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Масса
        TextField mass_stator = create_textfield();
        into_database.add(mass_stator);
        grid.add(create_label("Масса ротора (т):"), 0, helper);
        grid.add(mass_stator, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField mass_rotor = create_textfield();
        into_database.add(mass_rotor);
        grid.add(create_label("Масса статора (т):"), 0, helper);
        grid.add(mass_rotor, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        TextField mass_total = create_textfield();
        into_database.add(mass_total);
        grid.add(create_label("Масса общая (т):"), 0, helper);
        grid.add(mass_total, 1, helper);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, helper);

        helper++;

        // Размеры
        TextField stator_diameter = create_textfield();
        into_database.add(stator_diameter);
        grid.add(create_label("Диаметр статора\nпо корпусу (мм):"), 0, helper);
        grid.add(stator_diameter, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        TextField rotor_diameter = create_textfield();
        into_database.add(rotor_diameter);
        grid.add(create_label("Диаметр ротора\n(мм):"), 0, helper);
        grid.add(rotor_diameter, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        TextField generator_length = create_textfield();
        into_database.add(generator_length);
        grid.add(create_label("Полная высота\n(длинна) генер-\nатора (мм):"), 0, helper);
        grid.add(generator_length, 1, helper);
        grid.add(create_helper_label("Впиши целое число"), 2, helper);

        helper++;

        // Параметры какие-то
        TextField execution_type = create_textfield();
        into_database.add(execution_type);
        grid.add(create_label("Исполнение:"), 0, helper);
        grid.add(execution_type, 1, helper);
        grid.add(create_helper_label("Впиши тип исполнения"), 2, helper);

        helper++;

        TextField exciter_type = create_textfield();
        into_database.add(exciter_type);
        grid.add(create_label("Тип возбудителя:"), 0, helper);
        grid.add(exciter_type, 1, helper);
        grid.add(create_helper_label("Впиши тип возбудителя"), 2, helper);

        helper++;

        TextField turbine = create_textfield();
        into_database.add(turbine);
        grid.add(create_label("Турбина:"), 0, helper);
        grid.add(turbine, 1, helper);
        grid.add(create_helper_label("Впиши тип турбины"), 2, helper);

        helper++;

        Button save_to_database = new Button("Сохранить");
        save_to_database.setId("save_gen");
        save_to_database.setOnAction(e -> save_generator1_to_database(into_database, 1));
        grid.add(save_to_database, 3, helper);

        centerPanel.getChildren().addAll(scroll_pane);
    }

    @FXML
    private void onAddGeneratorClick2()
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
        into_database.add(s_nom);
        grid.add(create_label("Sном (МВ * А):"), 0, 1);
        grid.add(s_nom, 1, 1);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 1);

        TextField p_nom = create_textfield();
        into_database.add(p_nom);
        grid.add(create_label("Pном (МВт):"), 0, 2);
        grid.add(p_nom, 1, 2);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 2);

        TextField cos_f_nom = create_textfield();
        into_database.add(cos_f_nom);
        grid.add(create_label("cosφном (%):"), 0, 3);
        grid.add(cos_f_nom, 1, 3);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, 3);

        TextField u_nom = create_textfield();
        into_database.add(u_nom);
        grid.add(create_label("Uном (кВ):"), 0, 4);
        grid.add(u_nom, 1, 4);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 4);

        TextField i_nom = create_textfield();
        into_database.add(i_nom);
        grid.add(create_label("Iном (кА):"), 0, 5);
        grid.add(i_nom, 1, 5);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 5);

        TextField n_nom = create_textfield();
        into_database.add(n_nom);
        grid.add(create_label("nном (об/мин):"), 0, 6);
        grid.add(n_nom, 1, 6);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 6);

        TextField n_ug_n_no = create_textfield();
        into_database.add(n_ug_n_no);
        grid.add(create_label("nуг/nном:"), 0, 7);
        grid.add(n_ug_n_no, 1, 7);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 7);

        TextField okz = create_textfield();
        into_database.add(okz);
        grid.add(create_label("ОКЗ:"), 0, 8);
        grid.add(okz, 1, 8);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 8);

        // Индуктивные сопротивления
        TextField x_dd = create_textfield();
        into_database.add(x_dd);
        grid.add(create_label("x''d:"), 0, 9);
        grid.add(x_dd, 1, 9);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 9);

        TextField x_d = create_textfield();
        into_database.add(x_d);
        grid.add(create_label("x'd:"), 0, 10);
        grid.add(x_d, 1, 10);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 10);

        TextField x_d_long = create_textfield();
        into_database.add(x_d_long);
        grid.add(create_label("xd:"), 0, 11);
        grid.add(x_d_long, 1, 11);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 11);

        TextField x_qq = create_textfield();
        into_database.add(x_qq);
        grid.add(create_label("x''q:"), 0, 12);
        grid.add(x_qq, 1, 12);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 12);

        TextField x_q = create_textfield();
        into_database.add(x_q);
        grid.add(create_label("xq:"), 0, 13);
        grid.add(x_q, 1, 13);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 13);

        TextField x2 = create_textfield();
        into_database.add(x2);
        grid.add(create_label("x2:"), 0, 14);
        grid.add(x2, 1, 14);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 14);

        TextField x0 = create_textfield();
        into_database.add(x0);
        grid.add(create_label("x0:"), 0, 15);
        grid.add(x0, 1, 15);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 15);

        // Время
        TextField t_d0 = create_textfield();
        into_database.add(t_d0);
        grid.add(create_label("Td0 (с):"), 0, 16);
        grid.add(t_d0, 1, 16);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 16);

        // Возбуждение
        TextField if_nom = create_textfield();
        into_database.add(if_nom);
        grid.add(create_label("Ifном (А):"), 0, 17);
        grid.add(if_nom, 1, 17);
        grid.add(create_helper_label("Впиши целое число"), 2, 17);

        TextField if_nom_ix = create_textfield();
        into_database.add(if_nom_ix);
        grid.add(create_label("Ifном/Ix (x):"), 0, 18);
        grid.add(if_nom_ix, 1, 18);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 18);

        TextField uf_nom = create_textfield();
        into_database.add(uf_nom);
        grid.add(create_label("Ufном (В):"), 0, 19);
        grid.add(uf_nom, 1, 19);
        grid.add(create_helper_label("Впиши целое число"), 2, 19);

        TextField forcing_ratio = create_textfield();
        into_database.add(forcing_ratio);
        grid.add(create_label("Кратность\nфорсировки:"), 0, 20);
        grid.add(forcing_ratio, 1, 20);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 20);

        TextField excitation_system = create_textfield();
        into_database.add(excitation_system);
        grid.add(create_label("Система\nвозбуждения:"), 0, 21);
        grid.add(excitation_system, 1, 21);
        grid.add(create_helper_label("Впиши название системы"), 2, 21);

        // Коэффициенты
        TextField n_percent = create_textfield();
        into_database.add(n_percent);
        grid.add(create_label("ŋ (%):"), 0, 22);
        grid.add(n_percent, 1, 22);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, 22);

        TextField j = create_textfield();
        into_database.add(j);
        grid.add(create_label("J (т * m2 * 0.25):"), 0, 23);
        grid.add(j, 1, 23);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 23);

        // Масса
        TextField mass_stator = create_textfield();
        into_database.add(mass_stator);
        grid.add(create_label("Масса ротора (т):"), 0, 24);
        grid.add(mass_stator, 1, 24);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 24);

        TextField mass_rotor = create_textfield();
        into_database.add(mass_rotor);
        grid.add(create_label("Масса статора (т):"), 0, 25);
        grid.add(mass_rotor, 1, 25);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 25);

        TextField mass_total = create_textfield();
        into_database.add(mass_total);
        grid.add(create_label("Масса общая (т):"), 0, 26);
        grid.add(mass_total, 1, 26);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 26);


        // Размеры
        TextField stator_diameter = create_textfield();
        into_database.add(stator_diameter);
        grid.add(create_label("Диаметр статора\nпо корпусу (мм):"), 0, 27);
        grid.add(stator_diameter, 1, 27);
        grid.add(create_helper_label("Впиши целое число"), 2, 27);

        TextField rotor_diameter = create_textfield();
        into_database.add(rotor_diameter);
        grid.add(create_label("Диаметр ротора\n(мм):"), 0, 28);
        grid.add(rotor_diameter, 1, 28);
        grid.add(create_helper_label("Впиши целое число"), 2, 28);

        TextField generator_length = create_textfield();
        into_database.add(generator_length);
        grid.add(create_label("Полная высота\n(длинна) генер-\nатора (мм):"), 0, 29);
        grid.add(generator_length, 1, 29);
        grid.add(create_helper_label("Впиши целое число"), 2, 29);

        // Параметры какие-то
        TextField execution_type = create_textfield();
        into_database.add(execution_type);
        grid.add(create_label("Исполнение:"), 0, 30);
        grid.add(execution_type, 1, 30);
        grid.add(create_helper_label("Впиши тип исполнения"), 2, 30);

        TextField exciter_type = create_textfield();
        into_database.add(exciter_type);
        grid.add(create_label("Тип возбудителя:"), 0, 31);
        grid.add(exciter_type, 1, 31);
        grid.add(create_helper_label("Впиши тип возбудителя"), 2, 31);

        TextField turbine = create_textfield();
        into_database.add(turbine);
        grid.add(create_label("Турбина:"), 0, 32);
        grid.add(turbine, 1, 32);
        grid.add(create_helper_label("Впиши тип турбины"), 2, 32);

        Button save_to_database = new Button("Сохранить");
        save_to_database.setId("save_gen");
        save_to_database.setOnAction(e -> save_generator2_to_database(into_database));
        grid.add(save_to_database, 3, 33);

        centerPanel.getChildren().addAll(scroll_pane);
    }

    private float parse_float(String value)
    {
        if (value == null || value.trim().isEmpty())
        {
            return (float) 0.0;
        }
        try
        {
            return Float.parseFloat(value);
        }
        catch (NumberFormatException e)
        {
            return (float) 0.0;
        }
    }

    private int parse_int(String value)
    {
        if(value == null || value.trim().isEmpty())
        {
            return 0;
        }
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }

    private void save_autotransformator_to_database(List<TextField> into_database)
    {
        String url = "jdbc:postgresql://localhost:5432/KhPI";
        String user = "postgres";
        String password = "Ffdss321!";

        String type = into_database.get(0).getText();
        String s_nom = into_database.get(1).getText();
        String s_nn = into_database.get(2).getText();
        String bn = into_database.get(3).getText();
        String cn = into_database.get(4).getText();
        String hh = into_database.get(5).getText();
        String pxxA = into_database.get(6).getText();
        String pxxB = into_database.get(7).getText();
        String bhch = into_database.get(8).getText();
        String bhhh = into_database.get(9).getText();
        String chhh = into_database.get(10).getText();
        String Ubhch = into_database.get(11).getText();
        String Ubhhh = into_database.get(12).getText();
        String Uchhh = into_database.get(13).getText();
        String Ixx = into_database.get(14).getText();
        String dlina = into_database.get(15).getText();
        String shirina = into_database.get(16).getText();
        String vysota_do = into_database.get(17).getText();
        String vysota_posle = into_database.get(18).getText();
        String maslo = into_database.get(19).getText();
        String transport = into_database.get(20).getText();
        String polna = into_database.get(21).getText();
        String suma = into_database.get(22).getText();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO autotransformators (type, s_nom, s_nn, bn, cn, hh, pxxA, pxxB, bhch, " +
                    "bhhh, chhh, Ubhch, Ubhhh, Uchhh, Ixx, dlina, shirina, vysota_do, vysota_posle, maslo, transport, polna, " +
                    "suma) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, type);
                stmt.setFloat(2, parse_float(s_nom));
                stmt.setFloat(3, parse_float(s_nn));
                stmt.setFloat(4, parse_float(bn));
                stmt.setFloat(5, parse_float(cn));
                stmt.setFloat(6, parse_float(hh));
                stmt.setFloat(7, parse_float(pxxA));
                stmt.setFloat(8, parse_float(pxxB));
                stmt.setFloat(9, parse_float(bhch));
                stmt.setFloat(10, parse_float(bhhh));
                stmt.setFloat(11, parse_float(chhh));
                stmt.setFloat(12, parse_float(Ubhch));
                stmt.setFloat(13, parse_float(Ubhhh));
                stmt.setFloat(14, parse_float(Uchhh));
                stmt.setFloat(15, parse_float(Ixx));
                stmt.setFloat(16, parse_float(dlina));
                stmt.setFloat(17, parse_float(shirina));
                stmt.setFloat(18, parse_float(vysota_do));
                stmt.setFloat(19, parse_float(vysota_posle));
                stmt.setFloat(20, parse_float(maslo));
                stmt.setFloat(21, parse_float(transport));
                stmt.setFloat(22, parse_float(polna));
                stmt.setFloat(23, parse_float(suma));

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " rows into the database autotransformators");
            }
            catch (SQLException e)
            {
                System.out.println("Connection failed(autotransformators): " + e.getMessage());
            }
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed(autotransformators): " + e.getMessage());
        }
    }

    private void save_transformator_to_database(List<TextField> into_database)
    {
        String url = "jdbc:postgresql://localhost:5432/KhPI";
        String user = "postgres";
        String password = "Ffdss321!";

        String type = into_database.get(0).getText();
        String s_nom = into_database.get(1).getText();
        String s_nn = into_database.get(2).getText();
        String bn = into_database.get(3).getText();
        String cn = into_database.get(4).getText();
        String hh = into_database.get(5).getText();
        String pxxA = into_database.get(6).getText();
        String pxxB = into_database.get(7).getText();
        String bhch = into_database.get(8).getText();
        String bhhh = into_database.get(9).getText();
        String chhh = into_database.get(10).getText();
        String Ubhch = into_database.get(11).getText();
        String Ubhhh = into_database.get(12).getText();
        String Uchhh = into_database.get(13).getText();
        String Ixx = into_database.get(14).getText();
        String dlina = into_database.get(15).getText();
        String shirina = into_database.get(16).getText();
        String vysota_do = into_database.get(17).getText();
        String vysota_posle = into_database.get(18).getText();
        String maslo = into_database.get(19).getText();
        String transport = into_database.get(20).getText();
        String polna = into_database.get(21).getText();
        String suma = into_database.get(22).getText();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO transformators (type, s_nom, s_nn, bn, cn, hh, pxxA, pxxB, bhch, " +
                    "bhhh, chhh, Ubhch, Ubhhh, Uchhh, Ixx, dlina, shirina, vysota_do, vysota_posle, maslo, transport, polna, " +
                    "suma) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, type);
                stmt.setFloat(2, parse_float(s_nom));
                stmt.setFloat(3, parse_float(s_nn));
                stmt.setFloat(4, parse_float(bn));
                stmt.setFloat(5, parse_float(cn));
                stmt.setFloat(6, parse_float(hh));
                stmt.setFloat(7, parse_float(pxxA));
                stmt.setFloat(8, parse_float(pxxB));
                stmt.setFloat(9, parse_float(bhch));
                stmt.setFloat(10, parse_float(bhhh));
                stmt.setFloat(11, parse_float(chhh));
                stmt.setFloat(12, parse_float(Ubhch));
                stmt.setFloat(13, parse_float(Ubhhh));
                stmt.setFloat(14, parse_float(Uchhh));
                stmt.setFloat(15, parse_float(Ixx));
                stmt.setFloat(16, parse_float(dlina));
                stmt.setFloat(17, parse_float(shirina));
                stmt.setFloat(18, parse_float(vysota_do));
                stmt.setFloat(19, parse_float(vysota_posle));
                stmt.setFloat(20, parse_float(maslo));
                stmt.setFloat(21, parse_float(transport));
                stmt.setFloat(22, parse_float(polna));
                stmt.setFloat(23, parse_float(suma));

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " rows into the database transformators");
            }
            catch (SQLException e)
            {
                System.out.println("Connection failed(transformators): " + e.getMessage());
            }
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed(transformators): " + e.getMessage());
        }
    }

    private void save_generator2_to_database(List<TextField> into_database)
    {
        String url = "jdbc:postgresql://localhost:5432/KhPI";
        String user = "postgres";
        String password = "Ffdss321!";

        String type = into_database.get(0).getText();
        String s_nom = into_database.get(1).getText();
        String p_nom = into_database.get(2).getText();
        String cos_f_nom = into_database.get(3).getText();
        String u_nom = into_database.get(4).getText();
        String i_nom = into_database.get(5).getText();
        String n_nom = into_database.get(6).getText();
        String n_ug_n_no = into_database.get(7).getText();
        String okz = into_database.get(8).getText();
        String x_dd = into_database.get(9).getText();
        String x_d = into_database.get(10).getText();
        String x_d_long = into_database.get(11).getText();
        String x_qq = into_database.get(12).getText();
        String x_q = into_database.get(13).getText();
        String x2 = into_database.get(14).getText();
        String x0 = into_database.get(15).getText();
        String t_d0 = into_database.get(16).getText();
        String if_nom = into_database.get(17).getText();
        String if_nom_ix = into_database.get(18).getText();
        String uf_nom = into_database.get(19).getText();
        String forcing_ratio = into_database.get(20).getText();
        String excitation_system = into_database.get(21).getText();
        String n_percent = into_database.get(22).getText();
        String j = into_database.get(23).getText();
        String mass_stator = into_database.get(24).getText();
        String mass_rotor = into_database.get(25).getText();
        String mass_total = into_database.get(26).getText();
        String stator_diameter = into_database.get(27).getText();
        String rotor_diameter = into_database.get(28).getText();
        String generator_length = into_database.get(29).getText();
        String execution_type = into_database.get(30).getText();
        String exciter_type = into_database.get(31).getText();
        String turbine = into_database.get(32).getText();

        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String query = "INSERT INTO generators2 (type, s_nom, p_nom, cos_f_nom, u_nom, i_nom, n_nom, n_ug_n_no, okz, " +
                    "x_dd, x_d, x_d_long, x_qq, x_q, x2, x0, t_d0, if_nom, if_nom_ix, uf_nom, forcing_ratio, excitation_system, " +
                    "n_percent, j, mass_stator, mass_rotor, mass_total, stator_diameter, rotor_diameter, generator_length, " +
                    "execution_type, exciter_type, turbine) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query))
            {
                stmt.setString(1, type);
                stmt.setFloat(2, parse_float(s_nom));
                stmt.setFloat(3, parse_float(p_nom));
                stmt.setFloat(4, parse_float(cos_f_nom));
                stmt.setFloat(5, parse_float(u_nom));
                stmt.setFloat(6, parse_float(i_nom));
                stmt.setFloat(7, parse_float(n_nom));
                stmt.setFloat(8, parse_float(n_ug_n_no));
                stmt.setFloat(9, parse_float(okz));
                stmt.setFloat(10, parse_float(x_dd));
                stmt.setFloat(11, parse_float(x_d));
                stmt.setFloat(12, parse_float(x_d_long));
                stmt.setFloat(13, parse_float(x_qq));
                stmt.setFloat(14, parse_float(x_q));
                stmt.setFloat(15, parse_float(x2));
                stmt.setFloat(16, parse_float(x0));
                stmt.setFloat(17, parse_float(t_d0));
                stmt.setFloat(18, parse_float(if_nom));
                stmt.setFloat(19, parse_float(if_nom_ix));
                stmt.setFloat(20, parse_float(uf_nom));
                stmt.setFloat(21, parse_float(forcing_ratio));
                stmt.setString(22, excitation_system);
                stmt.setFloat(23, parse_float(n_percent));
                stmt.setFloat(24, parse_float(j));
                stmt.setFloat(25, parse_float(mass_stator));
                stmt.setFloat(26, parse_float(mass_rotor));
                stmt.setFloat(27, parse_float(mass_total));
                stmt.setInt(28, parse_int(stator_diameter));
                stmt.setInt(29, parse_int(rotor_diameter));
                stmt.setInt(30, parse_int(generator_length));
                stmt.setString(31, execution_type);
                stmt.setString(32, exciter_type);
                stmt.setString(33, turbine);

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " rows into the database generators");

            }
            catch (SQLException e)
            {
                System.out.println("Error during insert(generators): " + e.getMessage());
            }
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed(generators): " + e.getMessage());
        }
    }

    private void save_generator1_to_database(List<TextField> into_database, int what)
    {
        String url = "jdbc:postgresql://localhost:5432/KhPI";
        String user = "postgres";
        String password = "Ffdss321!";

        String type = into_database.get(0).getText();
        String s_nom = into_database.get(1).getText();
        String p_nom = into_database.get(2).getText();
        String cos_f_nom = into_database.get(3).getText();
        String u_nom = into_database.get(4).getText();
        String i_nom = into_database.get(5).getText();
        String n_nom = into_database.get(6).getText();
        String n_ug_n_no = into_database.get(7).getText();
        String okz = into_database.get(8).getText();
        String x_dd = into_database.get(9).getText();
        String x_d = into_database.get(10).getText();
        String x_d_long = into_database.get(11).getText();
        String x_qq = into_database.get(12).getText();
        String x_q = into_database.get(13).getText();
        String x2 = into_database.get(14).getText();
        String x0 = into_database.get(15).getText();
        String t_d0 = into_database.get(16).getText();
        String if_nom = into_database.get(17).getText();
        String if_nom_ix = into_database.get(18).getText();
        String uf_nom = into_database.get(19).getText();
        String forcing_ratio = into_database.get(20).getText();
        String excitation_system = into_database.get(21).getText();
        String n_percent = into_database.get(22).getText();
        String j = into_database.get(23).getText();
        String mass_stator = into_database.get(24).getText();
        String mass_rotor = into_database.get(25).getText();
        String mass_total = into_database.get(26).getText();
        String stator_diameter = into_database.get(27).getText();
        String rotor_diameter = into_database.get(28).getText();
        String generator_length = into_database.get(29).getText();
        String execution_type = into_database.get(30).getText();
        String exciter_type = into_database.get(31).getText();
        String turbine = into_database.get(32).getText();

        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String query = "INSERT INTO generators1 (type, s_nom, p_nom, cos_f_nom, u_nom, i_nom, n_nom, n_ug_n_no, okz, " +
                    "x_dd, x_d, x_d_long, x_qq, x_q, x2, x0, t_d0, if_nom, if_nom_ix, uf_nom, forcing_ratio, excitation_system, " +
                    "n_percent, j, mass_stator, mass_rotor, mass_total, stator_diameter, rotor_diameter, generator_length, " +
                    "execution_type, exciter_type, turbine) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query))
            {
                stmt.setString(1, type);
                stmt.setFloat(2, parse_float(s_nom));
                stmt.setFloat(3, parse_float(p_nom));
                stmt.setFloat(4, parse_float(cos_f_nom));
                stmt.setFloat(5, parse_float(u_nom));
                stmt.setFloat(6, parse_float(i_nom));
                stmt.setFloat(7, parse_float(n_nom));
                stmt.setFloat(8, parse_float(n_ug_n_no));
                stmt.setFloat(9, parse_float(okz));
                stmt.setFloat(10, parse_float(x_dd));
                stmt.setFloat(11, parse_float(x_d));
                stmt.setFloat(12, parse_float(x_d_long));
                stmt.setFloat(13, parse_float(x_qq));
                stmt.setFloat(14, parse_float(x_q));
                stmt.setFloat(15, parse_float(x2));
                stmt.setFloat(16, parse_float(x0));
                stmt.setFloat(17, parse_float(t_d0));
                stmt.setFloat(18, parse_float(if_nom));
                stmt.setFloat(19, parse_float(if_nom_ix));
                stmt.setFloat(20, parse_float(uf_nom));
                stmt.setFloat(21, parse_float(forcing_ratio));
                stmt.setString(22, excitation_system);
                stmt.setFloat(23, parse_float(n_percent));
                stmt.setFloat(24, parse_float(j));
                stmt.setFloat(25, parse_float(mass_stator));
                stmt.setFloat(26, parse_float(mass_rotor));
                stmt.setFloat(27, parse_float(mass_total));
                stmt.setInt(28, parse_int(stator_diameter));
                stmt.setInt(29, parse_int(rotor_diameter));
                stmt.setInt(30, parse_int(generator_length));
                stmt.setString(31, execution_type);
                stmt.setString(32, exciter_type);
                stmt.setString(33, turbine);

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " rows into the database generators");

            }
            catch (SQLException e)
            {
                System.out.println("Error during insert(generators): " + e.getMessage());
            }
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed(generators): " + e.getMessage());
        }
    }

    private void save_generator_to_database(List<TextField> into_database)
    {
        String url = "jdbc:postgresql://localhost:5432/KhPI";
        String user = "postgres";
        String password = "Ffdss321!";

        String type = into_database.get(0).getText();
        String s_nom = into_database.get(1).getText();
        String p_nom = into_database.get(2).getText();
        String cos_f_nom = into_database.get(3).getText();
        String u_nom = into_database.get(4).getText();
        String i_nom = into_database.get(5).getText();
        String n_nom = into_database.get(6).getText();
        String n_ug_n_no = into_database.get(7).getText();
        String okz = into_database.get(8).getText();
        String x_dd = into_database.get(9).getText();
        String x_d = into_database.get(10).getText();
        String x_d_long = into_database.get(11).getText();
        String x_qq = into_database.get(12).getText();
        String x_q = into_database.get(13).getText();
        String x2 = into_database.get(14).getText();
        String x0 = into_database.get(15).getText();
        String t_d0 = into_database.get(16).getText();
        String if_nom = into_database.get(17).getText();
        String if_nom_ix = into_database.get(18).getText();
        String uf_nom = into_database.get(19).getText();
        String forcing_ratio = into_database.get(20).getText();
        String excitation_system = into_database.get(21).getText();
        String n_percent = into_database.get(22).getText();
        String j = into_database.get(23).getText();
        String mass_stator = into_database.get(24).getText();
        String mass_rotor = into_database.get(25).getText();
        String mass_total = into_database.get(26).getText();
        String stator_diameter = into_database.get(27).getText();
        String rotor_diameter = into_database.get(28).getText();
        String generator_length = into_database.get(29).getText();
        String execution_type = into_database.get(30).getText();
        String exciter_type = into_database.get(31).getText();
        String turbine = into_database.get(32).getText();

        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String query = "INSERT INTO generators (type, s_nom, p_nom, cos_f_nom, u_nom, i_nom, n_nom, n_ug_n_no, okz, " +
                    "x_dd, x_d, x_d_long, x_qq, x_q, x2, x0, t_d0, if_nom, if_nom_ix, uf_nom, forcing_ratio, excitation_system, " +
                    "n_percent, j, mass_stator, mass_rotor, mass_total, stator_diameter, rotor_diameter, generator_length, " +
                    "execution_type, exciter_type, turbine) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query))
            {
                stmt.setString(1, type);
                stmt.setFloat(2, parse_float(s_nom));
                stmt.setFloat(3, parse_float(p_nom));
                stmt.setFloat(4, parse_float(cos_f_nom));
                stmt.setFloat(5, parse_float(u_nom));
                stmt.setFloat(6, parse_float(i_nom));
                stmt.setFloat(7, parse_float(n_nom));
                stmt.setFloat(8, parse_float(n_ug_n_no));
                stmt.setFloat(9, parse_float(okz));
                stmt.setFloat(10, parse_float(x_dd));
                stmt.setFloat(11, parse_float(x_d));
                stmt.setFloat(12, parse_float(x_d_long));
                stmt.setFloat(13, parse_float(x_qq));
                stmt.setFloat(14, parse_float(x_q));
                stmt.setFloat(15, parse_float(x2));
                stmt.setFloat(16, parse_float(x0));
                stmt.setFloat(17, parse_float(t_d0));
                stmt.setFloat(18, parse_float(if_nom));
                stmt.setFloat(19, parse_float(if_nom_ix));
                stmt.setFloat(20, parse_float(uf_nom));
                stmt.setFloat(21, parse_float(forcing_ratio));
                stmt.setString(22, excitation_system);
                stmt.setFloat(23, parse_float(n_percent));
                stmt.setFloat(24, parse_float(j));
                stmt.setFloat(25, parse_float(mass_stator));
                stmt.setFloat(26, parse_float(mass_rotor));
                stmt.setFloat(27, parse_float(mass_total));
                stmt.setInt(28, parse_int(stator_diameter));
                stmt.setInt(29, parse_int(rotor_diameter));
                stmt.setInt(30, parse_int(generator_length));
                stmt.setString(31, execution_type);
                stmt.setString(32, exciter_type);
                stmt.setString(33, turbine);

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " rows into the database generators");

            }
            catch (SQLException e)
            {
                System.out.println("Error during insert(generators): " + e.getMessage());
            }
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed(generators): " + e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
}