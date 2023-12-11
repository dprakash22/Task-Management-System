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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class delete implements Initializable {

    @FXML
    private Button delbtn;

    @FXML
    private Button compbtn;

    @FXML
    private TextField compfield;

    @FXML
    private Button backbtn;

    @FXML
    private TextField delfield;

    @FXML
    private TableView<deletetable> deltable;

    @FXML
    private TableColumn<deletetable, String> deltitle;

    @FXML
    private TableColumn<deletetable, String> desctitle;

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
            connection = (java.sql.Connection) DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void table() {
        ObservableList<deletetable> tableItems = FXCollections.observableArrayList();
        String url = "jdbc:mysql://localhost:3306/project";
        String pass = "Dprakash2004@";
        String user = "root";
        System.out.println("table is called");
        try {

            LocalDate date = LocalDate.now();


            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT title,description from tasks where user_id = " + 1;

            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            {
                while (rs.next()) {
                    deletetable tabledatavalue = new deletetable();
                    tabledatavalue.setname(rs.getString("title"));
                    tabledatavalue.setdesc(rs.getString("description"));
                    tableItems.add(tabledatavalue);
                }
            }

            deltable.setItems(tableItems);
            desctitle.setCellValueFactory(f -> f.getValue().descProperty());
            deltitle.setCellValueFactory(f -> f.getValue().titleProperty());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void showItemDetails(Maintable item) {
        if (item != null) {
            textFieldTitle.setText(item.getName());
            textFieldDesc.setText(item.getdesc());
        }
    }


    @FXML
    void delbtnclick(ActionEvent event) throws SQLException {

            String tit = delfield.getText();
            String url = "jdbc:mysql://localhost:3306/project";
            String pass = "Dprakash2004@";
            String user = "root";
            String query = "DELETE FROM tasks WHERE title = \"" + tit + "\" ";

            Connection con = DriverManager.getConnection(url,user,pass);
            Statement pt = con.createStatement();
            int rs = pt.executeUpdate(query);
            System.out.println("Row deleted" + rs);
            con.close();
            table();
    }

    @FXML
    void backbtnclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("maintaskpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void compbtnclick(ActionEvent event) throws SQLException {

        String tit = compfield.getText();
        String url = "jdbc:mysql://localhost:3306/project";
        String pass = "Dprakash2004@";
        String user = "root";
        String query = "UPDATE tasks SET status = \"completed\" WHERE title = \"" + tit + "\" ";

        Connection con = DriverManager.getConnection(url,user,pass);
        Statement pt = con.createStatement();
        int rs = pt.executeUpdate(query);
        System.out.println("Row update to complete" + rs);
        con.close();
        table();
    }
}
