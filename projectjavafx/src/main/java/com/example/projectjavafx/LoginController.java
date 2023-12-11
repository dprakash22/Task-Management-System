package com.example.projectjavafx;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
public class LoginController {
    public int data = 0;

    public static int userID ;


    @FXML
    private Label incorrectlabel;

    @FXML
    private Button loginbtn;

    @FXML
    public TextField password;

    @FXML
    public TextField user;

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }

    public TextField getUser() {
        return user;
    }

    public void setUser(TextField user) {
        this.user = user;
    }

    public int login(String name, String Pass) throws Exception {

        String url = "jdbc:mysql://localhost:3306/project";
        String pass = "Dprakash2004@";
        String user = "root";
        String query = "SELECT * FROM user";

        String user_name = name;
        String password = Pass;
        String user_pass = "select password from user where name =\""+user_name+"\"";
        int user_id = 0;

        Connection con = DriverManager.getConnection(url,user,pass);
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();

        PreparedStatement pt = con.prepareStatement(user_pass);
        ResultSet ks = pt.executeQuery();

        while (rs.next()){

            String check = rs.getString(4);
            ks.next();
            if (user_name.equals(check)){
                System.out.println("This username is available");

                if(ks.getString(1).equals(password)){
                    System.out.println("Password also correct");
                    data = 1;
                    user_id = rs.getInt(1);

                }
                else{
                    System.out.println("Password is incorrect");
                }
            }
        }
        con.close();
        return user_id;
    }
//    int user_id;
//
//    public void setuserid(){
//        user_id=this.user_id;
//    }

    @FXML
    void loginbtnclick(ActionEvent event) throws Exception {

        String username = user.getText();
        String userpass = password.getText();

        int user_id = login(username,userpass);
        if (data == 1) {
//            setuserid(user_id);
            userID=user_id;
            Parent root = FXMLLoader.load(getClass().getResource("maintaskpage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            incorrectlabel.setText("                            Login Successfully ");
        }
        else{
            incorrectlabel.setText("                            !Invalid Login ");
        }
    }





    @FXML
    void registerationclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
