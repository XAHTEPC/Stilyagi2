package com.example.demo;

import com.example.demo.Database.DataBase;
import com.example.demo.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class HelloApplication extends Application {


    int i=0;
    static String id="";
    static String user_role = "";
    static  String en_role ="";
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 800);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getStyle();
        FileInputStream Url;
        Image url;

        Url = new FileInputStream("png/Register.png");
        url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        root.getChildren().add(front);

        TextField  input_login = new TextField();
        input_login.setMinWidth(422);
        input_login.setMaxWidth(422);
        input_login.setMinHeight(53);
        input_login.setMaxHeight(53);
        input_login.setLayoutX(161);
        input_login.setLayoutY(377);
        input_login.setPromptText("login");
        input_login.setFont(Font.font("Verdana", 20));
        input_login.setBackground(null);
        root.getChildren().add(input_login);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefSize(422,53);
        passwordField.setBackground(null);
        passwordField.setPromptText("password");
        passwordField.setLayoutX(161);
        passwordField.setLayoutY(450);
        passwordField.setFont(Font.font("STXihei", 20));
        passwordField.setSkin(new PasswordFieldSkin(passwordField));
        root.getChildren().add(passwordField);

        Button enter = new Button();
        enter.setLayoutX(163);
        enter.setLayoutY(571);
        enter.setMinWidth(420);
        enter.setMinHeight(50);
        enter.setBackground(null);
        root.getChildren().add(enter);
        input_login.setFont(Font.font("STXihei",26));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(195);
        scrollPane.setLayoutY(105);
        scrollPane.setMaxHeight(500);
        scrollPane.setMaxWidth(900);
        scrollPane.setMinHeight(500);
        scrollPane.setMinWidth(900);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: #FFffff;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        Pane pane = new Pane();
        scrollPane.setContent(pane);


        enter.setOnAction(v -> {
            String log = input_login.getText();
            String pas = passwordField.getText();
            //String log = "UlianaKozlova@work.ru";
            //String pas = "6DmFB5";

            int lvl;
            String rol = "";
            DataBase dataBase;
            try {
                System.out.println(pas);
                //lvl=1;
                dataBase = new DataBase(log, pas);
                rol = dataBase.getRole();
                lvl = getLVL(rol);
                System.out.println(lvl);
                System.out.println(rol);
            } catch (SQLException e) {
                System.out.println("ERROR");
                passwordField.setText("");
                input_login.setText("Неверный логин или пароль");
                return;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            root.getChildren().removeAll(front);
            root.getChildren().removeAll(enter, passwordField, input_login);

            Text login = new Text();
            login.setLayoutX(0);
            login.setLayoutY(740);
            login.setTextAlignment(TextAlignment.CENTER);
            login.setFont(Font.font("STXihei",26));
            try {
                login.setText(DataBase.getEmployee_byID2(id));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


            Text role = new Text();
            role.setLayoutX(0);
            role.setLayoutY(780);
            role.setText(en_role);
            role.setFont(Font.font("STXihei",26));


            ImageView title1;


            if (lvl == 1) {///staff
                try {
                    FileInputStream Url1 = new FileInputStream("png/Client_Manager.png");
                    Image url1 = new Image(Url1);
                    title1 = new ImageView(url1);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                title1.setX(0);
                title1.setY(0);


                root.getChildren().addAll(title1);

                Button client = new Button();
                client.setLayoutX(459);
                client.setLayoutY(39);
                client.setMinHeight(17);
                client.setMinWidth(119);
                client.setBackground(null);
                root.getChildren().add(client);

                Button visit = new Button();
                visit.setLayoutX(633);
                visit.setLayoutY(39);
                visit.setMinHeight(17);
                visit.setMinWidth(108);
                visit.setBackground(null);
                root.getChildren().addAll(visit, role,login);

                client.setOnAction(value ->{
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);
                    Pane pane1 = new Pane();
                    try {
                        pane1 = Client_Staff.getPane(pane, scrollPane);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    scrollPane.setContent(pane1);
                    i=1;
                });
                visit.setOnAction(value ->{
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);

                    Pane pane1 = new Pane();
                    try {
                        pane1 = Visit_Staff.getPane(pane, scrollPane);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    scrollPane.setContent(pane1);
                    i=2;
                });

            } else
            if (lvl == 2) {///manager

                try {
                    FileInputStream Url1 = new FileInputStream("png/Client_Manager.png");
                    Image url1 = new Image(Url1);
                    title1 = new ImageView(url1);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                root.getChildren().addAll(title1);

                Button client = new Button();
                client.setLayoutX(459);
                client.setLayoutY(39);
                client.setMinHeight(17);
                client.setMinWidth(119);
                client.setBackground(null);
                root.getChildren().add(client);

                Button visit = new Button();
                visit.setLayoutX(633);
                visit.setLayoutY(39);
                visit.setMinHeight(17);
                visit.setMinWidth(108);
                visit.setBackground(null);
                root.getChildren().addAll(visit,role,login);

                client.setOnAction(value -> {
                    root.getChildren().removeAll(scrollPane);
                    Pane pane1 = new Pane();
                    root.getChildren().add(scrollPane);
                    try {
                        pane1 = Client_Manage.getPane(pane, scrollPane);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    scrollPane.setContent(pane1);
                    i=1;
                });

                visit.setOnAction(value -> {
                    root.getChildren().removeAll(scrollPane);
                    Pane pane1 = new Pane();
                    root.getChildren().addAll(scrollPane);
                    try {
                        pane1 = Visit_Manage.getPane(pane, scrollPane);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    scrollPane.setContent(pane1);
                    i=2;
                });
            } else
            if (lvl == 3) {///local_manager
                try {
                    FileInputStream Url1 = new FileInputStream("png/Local_Manager.png");
                    Image url1 = new Image(Url1);
                    title1 = new ImageView(url1);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                root.getChildren().addAll(title1);

                Button structure = new Button();
                structure.setLayoutX(325);
                structure.setLayoutY(44);
                structure.setMinHeight(17);
                structure.setMinWidth(150);
                structure.setBackground(null);

                Button employee = new Button();
                employee.setLayoutX(725);
                employee.setLayoutY(44);
                employee.setMinHeight(17);
                employee.setMinWidth(169);
                employee.setBackground(null);
                root.getChildren().addAll(employee,structure,role,login);
                structure.setOnAction(e -> {
                    root.getChildren().removeAll(scrollPane);
                    Pane pane1 = new Pane();
                    root.getChildren().add(scrollPane);
                    try {
                        pane1 = Structure_Local.getPane(pane, scrollPane);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    } catch (FileNotFoundException e1) {
                        throw new RuntimeException(e1);
                    } catch (ClassNotFoundException e1) {
                        throw new RuntimeException(e1);
                    }
                    scrollPane.setContent(pane1);
                });

                employee.setOnAction(e -> {
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);
                    Pane pane1 = new Pane();
                    try {
                        pane1 = Employee_Local.getPane(pane, scrollPane);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    } catch (FileNotFoundException e1) {
                        throw new RuntimeException(e1);
                    } catch (ClassNotFoundException e1) {
                        throw new RuntimeException(e1);
                    }
                    scrollPane.setContent(pane1);

                });
            } else if (lvl == 4) {///analyst

                try {
                    FileInputStream Url1 = new FileInputStream("png/Analyst.png");
                    Image url1 = new Image(Url1);
                    title1 = new ImageView(url1);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                title1.setX(0);
                title1.setX(0);
                root.getChildren().addAll(title1);
                root.getChildren().addAll(login, role);

                Button structure = new Button();
                structure.setLayoutX(169);
                structure.setLayoutY(44);
                structure.setMinWidth(150);
                structure.setMinHeight(17);
                structure.setBackground(null);

                Button service = new Button();
                service.setLayoutX(374);
                service.setLayoutY(44);
                service.setMinWidth(95);
                service.setMinHeight(17);
                service.setBackground(null);

                Button employee = new Button();
                employee.setLayoutX(524);
                employee.setLayoutY(44);
                employee.setMinWidth(169);
                employee.setMinHeight(17);
                employee.setBackground(null);

                Button client = new Button();
                client.setLayoutX(748);
                client.setLayoutY(44);
                client.setMinWidth(119);
                client.setMinHeight(17);
                client.setBackground(null);

                Button visit = new Button();
                visit.setLayoutX(922);
                visit.setLayoutY(44);
                visit.setMinWidth(108);
                visit.setMinHeight(17);
                visit.setBackground(null);
                root.getChildren().addAll(service,structure,employee,visit,client);
                service.setOnAction(e -> {
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);
                    Pane pane1 = new Pane();
                    try {
                        pane1 = Service_Analyst.getPane(pane, scrollPane);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    } catch (FileNotFoundException e1) {
                        throw new RuntimeException(e1);
                    } catch (ClassNotFoundException e1) {
                        throw new RuntimeException(e1);
                    }
                    scrollPane.setContent(pane1);
                });

                employee.setOnAction(e -> {
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);
                    Pane pane1 = new Pane();
                    try {
                        pane1 = Employee_Analyst.getPane(pane, scrollPane);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    } catch (FileNotFoundException e1) {
                        throw new RuntimeException(e1);
                    } catch (ClassNotFoundException e1) {
                        throw new RuntimeException(e1);
                    }
                    scrollPane.setContent(pane1);
                });

                client.setOnAction(e -> {
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);
                    Pane pane1 = new Pane();
                    try {
                        pane1 = Client_Analyst.getPane(pane, scrollPane);
                    } catch (SQLException er) {
                        throw new RuntimeException(er);
                    } catch (FileNotFoundException er) {
                        throw new RuntimeException(er);
                    } catch (ClassNotFoundException er) {
                        throw new RuntimeException(er);
                    }
                    scrollPane.setContent(pane1);
                });

                visit.setOnAction(e -> {
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);
                    Pane pane1 = new Pane();
                    try {
                        pane1 = Visit_Analyst.getPane(pane, scrollPane);
                    } catch (SQLException er) {
                        throw new RuntimeException(er);
                    } catch (FileNotFoundException er) {
                        throw new RuntimeException(er);
                    } catch (ClassNotFoundException er) {
                        throw new RuntimeException(er);
                    }
                    scrollPane.setContent(pane1);

                });
                structure.setOnAction(e -> {
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);
                    Pane pane1 = new Pane();
                    try {
                        pane1 = Structure_Analyst.getPane(pane, scrollPane);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    } catch (FileNotFoundException e1) {
                        throw new RuntimeException(e1);
                    } catch (ClassNotFoundException e1) {
                        throw new RuntimeException(e1);
                    }
                    scrollPane.setContent(pane1);
                    i=1;

                });

            } else if (lvl == 5) {///admin
                try {
                    FileInputStream Url1 = new FileInputStream("png/Admin.png");
                    Image url1 = new Image(Url1);
                    title1 = new ImageView(url1);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                root.getChildren().addAll(title1);
                Button employee = new Button();
                employee.setLayoutX(675);
                employee.setLayoutY(52);
                employee.setMinHeight(17);
                employee.setMinWidth(169);
                employee.setBackground(null);
                root.getChildren().addAll(employee);

                Button work = new Button();
                work.setLayoutX(374);
                work.setLayoutY(52);
                work.setMinHeight(17);
                work.setMinWidth(150);
                work.setBackground(null);
                root.getChildren().addAll(work,role,login);
                work.setOnAction(e ->{
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);
                    Pane pane1 = new Pane();
                    try {
                        pane1 = Work_Admin.getPane(pane,scrollPane);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    scrollPane.setContent(pane1);
                });

                employee.setOnAction(e -> {
                    root.getChildren().removeAll(scrollPane);
                    root.getChildren().addAll(scrollPane);
                    Pane pane1 = new Pane();
                    try {
                        pane1 = Employee_Admin.getPane(pane,scrollPane);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    scrollPane.setContent(pane1);
                });
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }

    public static int getLVL(String r) throws SQLException, FileNotFoundException, ClassNotFoundException {
        int lvl = 7;
        System.out.println(r);
        String user = DataBase.getCurrent_User();
        id = DataBase.getEmployee_byNAME(user);
        System.out.println("id:"+id);
        if(r.equals("Управляющий              ")){
            lvl = 3;
            user_role = "Управляющий";
            en_role = "Local_Manager";
        }
        else if(r.equals("Персонал                 ")){
            lvl = 1;
            user_role = "Персонал";
            en_role = "Staff";
        }
        else if(r.equals("Менеджер                 ")){
            lvl = 2;
            user_role = "Менеджер";
            en_role = "Manager";
        }
        else if(r.equals("Аналитик                 ")){
            lvl = 4;
            user_role = "Аналитик";
            en_role = "Analyst";
            System.out.println(user_role);
        }
        else {
            lvl = 5;
            user_role = "Администратор";
            en_role = "Administrator";
        }
        return lvl;
    }
}