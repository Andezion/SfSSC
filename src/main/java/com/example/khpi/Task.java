package com.example.khpi;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        grid.add(create_label("cosφном:"), 0, 3);
        grid.add(cos_f_nom, 1, 3);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, 3);

        TextField u_nom = create_textfield();
        into_database.add(u_nom);
        grid.add(create_label("Uном (кВ)"), 0, 4);
        grid.add(u_nom, 1, 4);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 4);

        TextField i_nom = create_textfield();
        into_database.add(i_nom);
        grid.add(create_label("Iном (кА)"), 0, 5);
        grid.add(i_nom, 1, 5);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 5);

        TextField n_nom = create_textfield();
        into_database.add(n_nom);
        grid.add(create_label("nном (об/мин)"), 0, 6);
        grid.add(n_nom, 1, 6);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 6);

        TextField n_ug_n_no = create_textfield();
        into_database.add(n_ug_n_no);
        grid.add(create_label("nуг/nном"), 0, 7);
        grid.add(n_ug_n_no, 1, 7);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 7);

        TextField okz = create_textfield();
        into_database.add(okz);
        grid.add(create_label("ОКЗ"), 0, 8);
        grid.add(okz, 1, 8);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 8);

        // Индуктивные сопротивления
        TextField x_dd = create_textfield();
        into_database.add(x_dd);
        grid.add(create_label("x''d"), 0, 9);
        grid.add(x_dd, 1, 9);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 9);

        TextField x_d = create_textfield();
        into_database.add(x_d);
        grid.add(create_label("x'd"), 0, 10);
        grid.add(x_d, 1, 10);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 10);

        TextField x_d_long = create_textfield();
        into_database.add(x_d_long);
        grid.add(create_label("xd"), 0, 11);
        grid.add(x_d_long, 1, 11);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 11);

        TextField x_qq = create_textfield();
        into_database.add(x_qq);
        grid.add(create_label("x''q"), 0, 12);
        grid.add(x_qq, 1, 12);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 12);

        TextField x_q = create_textfield();
        into_database.add(x_q);
        grid.add(create_label("xq"), 0, 13);
        grid.add(x_q, 1, 13);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 13);

        TextField x2 = create_textfield();
        into_database.add(x2);
        grid.add(create_label("x2"), 0, 14);
        grid.add(x2, 1, 14);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 14);

        TextField x0 = create_textfield();
        into_database.add(x0);
        grid.add(create_label("x0"), 0, 15);
        grid.add(x0, 1, 15);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 15);

        // Время
        TextField t_d0 = create_textfield();
        into_database.add(t_d0);
        grid.add(create_label("Td0 (с)"), 0, 16);
        grid.add(t_d0, 1, 16);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 16);

        // Возбуждение
        TextField if_nom = create_textfield();
        into_database.add(if_nom);
        grid.add(create_label("Ifном (А)"), 0, 17);
        grid.add(if_nom, 1, 17);
        grid.add(create_helper_label("Впиши целое число"), 2, 17);

        TextField if_nom_ix = create_textfield();
        into_database.add(if_nom_ix);
        grid.add(create_label("Ifном/Ix (x)"), 0, 18);
        grid.add(if_nom_ix, 1, 18);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 18);

        TextField uf_nom = create_textfield();
        into_database.add(uf_nom);
        grid.add(create_label("Ufном (В)"), 0, 19);
        grid.add(uf_nom, 1, 19);
        grid.add(create_helper_label("Впиши целое число"), 2, 19);

        TextField forcing_ratio = create_textfield();
        into_database.add(forcing_ratio);
        grid.add(create_label("Кратность\nфорсировки"), 0, 20);
        grid.add(forcing_ratio, 1, 20);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 20);

        TextField excitation_system = create_textfield();
        into_database.add(excitation_system);
        grid.add(create_label("Система\nвозбуждения"), 0, 21);
        grid.add(excitation_system, 1, 21);
        grid.add(create_helper_label("Впиши название системы"), 2, 21);

        // Коэффициенты
        TextField n_percent = create_textfield();
        into_database.add(n_percent);
        grid.add(create_label("ŋ (%)"), 0, 22);
        grid.add(n_percent, 1, 22);
        grid.add(create_helper_label("Впиши число от 0 до 100"), 2, 22);

        TextField j = create_textfield();
        into_database.add(j);
        grid.add(create_label("J (т * m2 * 0.25)"), 0, 23);
        grid.add(j, 1, 23);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 23);

        // Масса
        TextField mass_stator = create_textfield();
        into_database.add(mass_stator);
        grid.add(create_label("Масса ротора"), 0, 24);
        grid.add(mass_stator, 1, 24);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 24);

        TextField mass_rotor = create_textfield();
        into_database.add(mass_rotor);
        grid.add(create_label("Масса статора"), 0, 25);
        grid.add(mass_rotor, 1, 25);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 25);

        TextField mass_total = create_textfield();
        into_database.add(mass_total);
        grid.add(create_label("Масса общая"), 0, 26);
        grid.add(mass_total, 1, 26);
        grid.add(create_helper_label("Впиши число, если дробное то используй - \".\""), 2, 26);


        // Размеры
        TextField stator_diameter = create_textfield();
        into_database.add(stator_diameter);
        grid.add(create_label("Диаметр статора\nпо корпусу"), 0, 27);
        grid.add(stator_diameter, 1, 27);
        grid.add(create_helper_label("Впиши целое число"), 2, 27);

        TextField rotor_diameter = create_textfield();
        into_database.add(rotor_diameter);
        grid.add(create_label("Диаметр ротора"), 0, 28);
        grid.add(rotor_diameter, 1, 28);
        grid.add(create_helper_label("Впиши целое число"), 2, 28);

        TextField generator_length = create_textfield();
        into_database.add(generator_length);
        grid.add(create_label("Полная высота\n(длинна) генератора"), 0, 29);
        grid.add(generator_length, 1, 29);
        grid.add(create_helper_label("Впиши целое число"), 2, 29);

        // Параметры какие-то
        TextField execution_type = create_textfield();
        into_database.add(execution_type);
        grid.add(create_label("Исполнение"), 0, 30);
        grid.add(execution_type, 1, 30);
        grid.add(create_helper_label("Впиши тип исполнения"), 2, 30);

        TextField exciter_type = create_textfield();
        into_database.add(exciter_type);
        grid.add(create_label("Тип возбудителя"), 0, 31);
        grid.add(exciter_type, 1, 31);
        grid.add(create_helper_label("Впиши тип возбудителя"), 2, 31);

        TextField turbine = create_textfield();
        into_database.add(turbine);
        grid.add(create_label("Турбина"), 0, 32);
        grid.add(turbine, 1, 32);
        grid.add(create_helper_label("Впиши тип турбины"), 2, 32);

        Button save_to_database = new Button("Сохранить");
        save_to_database.setId("save_gen");
        save_to_database.setOnAction(e -> save_generator_to_database(into_database));
        grid.add(save_to_database, 3, 33);

        centerPanel.getChildren().addAll(scroll_pane);
    }

    private void save_generator_to_database(List<TextField> into_database)
    {
        String url = "jdbc:postgresql://localhost:5432/KhPI";
        String user = "postgres";
        String password = "Ffdss321!";

        // Пример получения данных из полей TextField
        String name_of_generator = into_database.get(0).getText();
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
            String query = "INSERT INTO generators (name_of_generator, s_nom, p_nom, cos_f_nom, u_nom, i_nom, n_nom, n_ug_n_no, okz, " +
                    "x_dd, x_d, x_d_long, x_qq, x_q, x2, x0, t_d0, if_nom, if_nom_ix, uf_nom, forcing_ratio, excitation_system, " +
                    "n_percent, j, mass_stator, mass_rotor, mass_total, stator_diameter, rotor_diameter, generator_length, " +
                    "execution_type, exciter_type, turbine) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query))
            {
                stmt.setString(1, name_of_generator);
                stmt.setString(2, s_nom);
                stmt.setString(3, p_nom);
                stmt.setString(4, cos_f_nom);
                stmt.setString(5, u_nom);
                stmt.setString(6, i_nom);
                stmt.setString(7, n_nom);
                stmt.setString(8, n_ug_n_no);
                stmt.setString(9, okz);
                stmt.setString(10, x_dd);
                stmt.setString(11, x_d);
                stmt.setString(12, x_d_long);
                stmt.setString(13, x_qq);
                stmt.setString(14, x_q);
                stmt.setString(15, x2);
                stmt.setString(16, x0);
                stmt.setString(17, t_d0);
                stmt.setString(18, if_nom);
                stmt.setString(19, if_nom_ix);
                stmt.setString(20, uf_nom);
                stmt.setString(21, forcing_ratio);
                stmt.setString(22, excitation_system);
                stmt.setString(23, n_percent);
                stmt.setString(24, j);
                stmt.setString(25, mass_stator);
                stmt.setString(26, mass_rotor);
                stmt.setString(27, mass_total);
                stmt.setString(28, stator_diameter);
                stmt.setString(29, rotor_diameter);
                stmt.setString(30, generator_length);
                stmt.setString(31, execution_type);
                stmt.setString(32, exciter_type);
                stmt.setString(33, turbine);

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " rows into the database.");

            }
            catch (SQLException e)
            {
                System.out.println("Error during insert: " + e.getMessage());
            }
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
}