package com.example.projectjavafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableItems {
    private final SimpleStringProperty startdatecomplete;
    private  final SimpleStringProperty enddatecomplete;
    private  final SimpleStringProperty titlecomplete;
    private  final SimpleStringProperty desccomplete;
    TableItems() {
        titlecomplete = new SimpleStringProperty(this, "titlecomplete");
        desccomplete = new SimpleStringProperty(this, "desccomplete");
        startdatecomplete = new SimpleStringProperty(this, "startdatecomplete");
        enddatecomplete = new SimpleStringProperty(this, "enddatecomplete");
    }

    public SimpleStringProperty titleProperty() { return titlecomplete; }
    public String getName() { return titlecomplete.get(); }
    public void setName(String newName) { titlecomplete.set(newName); }

    public SimpleStringProperty descProperty() { return desccomplete; }
    public String getdesc() { return desccomplete.get(); }
    public void setdesc(String newname) { desccomplete.set(newname); }


    public StringProperty startProperty() { return startdatecomplete; }
    public String getstdate() { return startdatecomplete.get(); }
    public void setstdate(String newName) { startdatecomplete.set(newName); }

    public StringProperty endProperty() { return enddatecomplete; }
    public String getedate() { return enddatecomplete.get(); }
    public void setedate(String newName) { enddatecomplete.set(newName); }
}
