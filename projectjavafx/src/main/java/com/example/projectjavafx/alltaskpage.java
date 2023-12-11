package com.example.projectjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.util.ResourceBundle;

public class alltaskpage implements Initializable {


    @FXML
    private TableColumn<TableItems, String> desccomplete;

    @FXML
    private TableColumn<TableItems, String> enddatecomplete;

    @FXML
    private TableColumn<TableItems, String> startdatecomplete;

    @FXML
    private TableView<TableItems> tableView;

    @FXML
    private TableColumn<TableItems, String> titlecomplete;


    @FXML
    private TextField textFieldStartDate;

    @FXML
    private TextField textFieldEndDate;

    @FXML
    private TextField textFieldTitle;

    @FXML
    private TextField textFielddesc;


    @FXML
    void backclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("maintaskpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
            connection = (java.sql.Connection) DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void table() {
        ObservableList<TableItems> tableItems = FXCollections.observableArrayList();
        String url = "jdbc:mysql://localhost:3306/project";
        String pass = "Dprakash2004@";
        String user = "root";
        System.out.println("table is called");
        try {
            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT title,start_date,end_date,description FROM tasks";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            {
                while (rs.next()) {
                    TableItems Tabledata = new TableItems();
                    Tabledata.setName(rs.getString("title"));
                    Tabledata.setstdate(rs.getString("start_date"));
                    Tabledata.setedate(rs.getString("end_date"));
                    Tabledata.setdesc(rs.getString("description"));
                    tableItems.add(Tabledata);
                }
            }

            tableView.setItems(tableItems);
            startdatecomplete.setCellValueFactory(f -> f.getValue().startProperty());
            enddatecomplete.setCellValueFactory(f -> f.getValue().endProperty());
            titlecomplete.setCellValueFactory(f -> f.getValue().titleProperty());
            desccomplete.setCellValueFactory(f -> f.getValue().descProperty());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void showItemDetails(TableItems item) {
        if (item != null) {
            textFieldStartDate.setText(item.getstdate());
            textFieldEndDate.setText(item.getedate());
            textFieldTitle.setText(item.getName());
            textFielddesc.setText(item.getdesc());
        }
    }
    @FXML
    void backclickbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("maintaskpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

