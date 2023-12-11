package com.example.projectjavafx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.sql.Connection;

import java.io.IOException;

public class register {

    @FXML
    public TextField Newpassword;

    @FXML
    public TextField confirmpassword;

    @FXML
    public TextField email;

    @FXML
    public TextField first;

    @FXML
    public TextField last;

    @FXML
    public Button registerbtn;

    @FXML
    public TextField username;

    public TextField getFirst() {
        return first;
    }

    public void setFirst(TextField first) {
        this.first = first;
    }

    public TextField getNewpassword() {
        return Newpassword;
    }

    public void setNewpassword(TextField newpassword) {
        Newpassword = newpassword;
    }



    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public TextField getLast() {
        return last;
    }

    public void setLast(TextField last) {
        this.last = last;
    }

    public Button getRegisterbtn() {
        return registerbtn;
    }

    public void setRegisterbtn(Button registerbtn) {
        this.registerbtn = registerbtn;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public static int Register(String first,String last,String name,String Email,String Password) throws Exception{
        String url = "jdbc:mysql://localhost:3306/project";
        String pass = "Dprakash2004@";
        String user = "root";

        Connection con = DriverManager.getConnection(url, user, pass);
        Statement getMaxIdStatement = con.createStatement();
        ResultSet maxIdResult = getMaxIdStatement.executeQuery("SELECT MAX(ID) FROM user");
        maxIdResult.next();

        int ID = maxIdResult.getInt(1) + 1;
        String first_name = first;
        String user_name = name;
        String email = Email;
        String last_name =last;
        String password = Password;
        String statement = "INSERT INTO user VALUES (?,?,?,?,?,?)";



        Statement st = con.createStatement();
        PreparedStatement ps = con.prepareStatement(statement);



        ps.setInt(1,ID);
        ps.setString(2,first_name);
        ps.setString(3,last_name);
        ps.setString(4,user_name);
        ps.setString(5,email);
        ps.setString(6,password);
        int row = ps.executeUpdate();

        st = con.createStatement();
        ResultSet rs = st.executeQuery("Select ID from user where name = \""+user_name+"\"");
        rs.next();
        return rs.getInt("ID");
    }
    @FXML
    void registerbtnclick(ActionEvent event) throws Exception {

        String firstName = first.getText();
        String lastName = last.getText();
        String userName = username.getText();
        String userEmail = email.getText();
        String userPassword = Newpassword.getText();

        int user_id = Register(firstName,lastName,userName,userEmail,userPassword);
        Parent root = FXMLLoader.load(getClass().getResource("maintaskpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

