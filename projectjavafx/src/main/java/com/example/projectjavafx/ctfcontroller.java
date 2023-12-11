package com.example.projectjavafx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import java.sql.*;
import java.sql.Connection;
import java.util.*;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
public class ctfcontroller implements Initializable {

    @FXML
    public TableColumn id;

    @FXML
    public TableColumn id1;
    @FXML
    private Label CTF;

    @FXML
    private VBox ctf1;

//    @Override
//    public void initialize(URL url, ResourceBundle rb, int userID) {
////        ctf1.setText("Hello");
//
//    }

    static LoginController lcu = new LoginController();
    public static int userID = lcu.userID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String urls = "jdbc:mysql://localhost:3306/project";
        String pass = "Dprakash2004@";
        String user = "root";
        String query = "SELECT * from tasks where user_id = " + userID;
//        String tr = "SELECT count(*) from tasks";

        try {

            Connection con = DriverManager.getConnection(urls, user, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
//                ResultSet yt = st.executeQuery(tr);
//            System.out.println(yt);

            while (rs.next()) {
                id.setText(rs.getString(2));
                id1.setText(rs.getString(3));
            }
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}


