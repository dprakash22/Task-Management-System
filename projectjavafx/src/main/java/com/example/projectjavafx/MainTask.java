package com.example.projectjavafx;

import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainTask implements Initializable{

    @FXML
    private Button alltaskbtn;

    @FXML
    private Button completed;

    @FXML
    private Button createbtn;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<Maintable, String> descmain;

    @FXML
    private TableView<Maintable> maintable;

    @FXML
    private ImageView menubar;

    @FXML
    private TableColumn<Maintable, String> prioritymain;

    @FXML
    private ImageView profile;

    @FXML
    private Button settings;

    @FXML
    private Button starred;

    @FXML
    private TableColumn<Maintable, String>startdatemain;

    @FXML
    private TableColumn<Maintable, String> titlemain;

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    @FXML
    private TextField textFieldStartDate;

    @FXML
    private TextField textFieldpriority;

    @FXML
    private TextField textFieldTitle;

    @FXML
    private TextField textFieldDesc;


    private java.sql.Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect();
        table();
    }

    private void connect() {
        try {
            // Initialize your database connection here
            String url = "jdbc:mysql://localhost:3306/project";
            String pass = "Dprakash2004@";
            String user = "root";
            connection = (Connection) DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void table() {
        ObservableList<Maintable> tableItems = FXCollections.observableArrayList();
        String url = "jdbc:mysql://localhost:3306/project";
        String pass = "Dprakash2004@";
        String user = "root";
        System.out.println("table is called");
        try {

            LocalDate date = LocalDate.now();


            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT * from tasks where user_id = " + 1 + " and end_date =  \"" + date + "\" ";

            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            {
                while (rs.next()) {
                    Maintable tabledatavalue = new Maintable();
                    tabledatavalue.setsdate(rs.getString("start_date"));
                    tabledatavalue.setname(rs.getString("title"));
                    tabledatavalue.setdesc(rs.getString("description"));
                    tabledatavalue.setpriorvalue(rs.getString("priority"));
                    tableItems.add(tabledatavalue);
                }
            }

            maintable.setItems(tableItems);
            startdatemain.setCellValueFactory(f -> f.getValue().startProperty());
            descmain.setCellValueFactory(f -> f.getValue().descProperty());
            prioritymain.setCellValueFactory(f -> f.getValue().priorProperty());
            titlemain.setCellValueFactory(f -> f.getValue().titleProperty());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void showItemDetails(Maintable item) {
        if (item != null) {
            textFieldStartDate.setText(item.getstdate());
            textFieldpriority.setText(item.getpriorvalue());
            textFieldTitle.setText(item.getName());
            textFieldDesc.setText(item.getdesc());
        }
    }


    @FXML
    void alltaskbtnclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("alltask.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void completedclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("completepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void createbtnclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("createpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("deletepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void menubarclick(MouseEvent event) throws IOException {
//
//        Parent root = FXMLLoader.load(getClass().getResource("maintaskpage.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    void settingsclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void starredclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("starred.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void profileclick(MouseEvent event) {

    }

}
