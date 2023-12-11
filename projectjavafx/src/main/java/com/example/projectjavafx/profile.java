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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ResourceBundle;

public class profile implements Initializable {

    @FXML
    private Button backbtn;

    @FXML
    private PieChart pie;

    @FXML
    private Label emailprofile;

    @FXML
    private Label fnameprofile;

    @FXML
    private Label lnameprofile;

    @FXML
    private Label userprofile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String u = "jdbc:mysql://localhost:3306/project";
        String pass = "Dprakash2004@";
        String user = "root";
        try{
            Connection conn = DriverManager.getConnection(u,user,pass);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM tasks WHERE end_date < curdate();");
            rs.next();
            int comp = rs.getInt(1);
            rs = st.executeQuery("select count(*) from tasks where status = \"completed\"");
            rs.next();
            int notC = rs.getInt(1);
            ObservableList<PieChart.Data> pcd = FXCollections.observableArrayList();
            PieChart.Data x= new PieChart.Data("Completed", comp);
            pcd.add(x);
            pcd.add(new PieChart.Data("Incomplete", notC));

            pie.getData().addAll(pcd);

//            for(Object x : pcd){
//                pie.getData().add((PieChart.Data)x);
//            }
//
//            pie.setTitle("Your Activity");
            pie.setLabelsVisible(true);
            pie.setStartAngle(180);

            int tot =comp + notC;

            for (PieChart.Data slice : pie.getData()) {
                slice.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                    Tooltip tp =new Tooltip(String.format("%.2f",(slice.getPieValue()/tot)*100)+"%");
                    Tooltip.install(slice.getNode(), tp);
                });
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void backbtnclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("maintaskpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
