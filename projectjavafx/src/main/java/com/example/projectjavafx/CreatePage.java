package com.example.projectjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
import java.sql.Connection;

public class CreatePage implements Initializable {
    
    static LoginController lcu = new LoginController();
    public static int userID = lcu.userID;

    @FXML
    public ChoiceBox<String> checkboxImp;

    @FXML
    private Button backbtn;

    @FXML
    public TextField description;

    @FXML
    public DatePicker enddate;

    @FXML
    public DatePicker startdate;

    @FXML
    public TextField title;

    private final String[] Priority ={"Low","Medium","High"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        checkboxImp.getItems().addAll(Priority);
        checkboxImp.setOnAction(this::getPriority);
    }

    public void getPriority(ActionEvent event){
        String Priority=checkboxImp.getValue();
    }

    public ChoiceBox<String> getCheckboxImp() {
        return checkboxImp;
    }

    public void setCheckboxImp(ChoiceBox<String> checkboxImp) {
        this.checkboxImp = checkboxImp;
    }

    public TextField getDescription() {
        return description;
    }

    public void setDescription(TextField description) {
        this.description = description;
    }

    public DatePicker getEnddate() {
        return enddate;
    }

    public void setEnddate(DatePicker enddate) {
        this.enddate = enddate;
    }

    public DatePicker getStartdate() {
        return startdate;
    }

    public void setStartdate(DatePicker startdate) {
        this.startdate = startdate;
    }

    public TextField getTitle() {
        return title;
    }

    public void setTitle(TextField title) {
        this.title = title;
    }

    public static void create_tasks(int userID,String tit,String des,String std,String enr,String prio,String sta) throws  Exception {
        String url = "jdbc:mysql://localhost:3306/project";
        String pass = "Dprakash2004@";
        String user = "root";

//            int id = 1;
        String title = tit;
        String description = des;
        String start = std;
        String end = enr;
        String priority =prio;
        String status = sta;
        int user_id = userID;
        String query = "INSERT INTO tasks  (title, description, start_date, end_date, priority, status, user_id) " +
                "VALUES(?,?,?,?,?,?,?)";

        Connection con = DriverManager.getConnection(url, user, pass);
        Statement st = con.createStatement();
        PreparedStatement ps = con.prepareStatement(query);

//            ps.setInt(1,id);
        ps.setString(1,title);
        ps.setString(2,description);
        ps.setString(3,start);
        ps.setString(4,end);
        ps.setString(5,priority);
        ps.setString(6,status);
        ps.setInt(7,user_id);

        int row = ps.executeUpdate();
        con.close();
    }

    @FXML
    void createdonebtnclick(ActionEvent event) throws Exception {
        String title = this.title.getText();
        String description = this.description.getText();
        String sdate = startdate.getValue().toString();
        String edate = enddate.getValue().toString();
        String priot = checkboxImp.getValue().toString();

        create_tasks(userID,title,description,sdate,edate,priot,"completed");
        Parent root = FXMLLoader.load(getClass().getResource("maintaskpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void backbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("maintaskpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
