package com.example.projectjavafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class deletetable {

    private final SimpleStringProperty desctitle;;
    private  final SimpleStringProperty deltitle;

    public deletetable() {
        deltitle = new SimpleStringProperty(this, "deltitle");
        desctitle = new SimpleStringProperty(this, "desctitle");

    }


    public SimpleStringProperty titleProperty() { return deltitle; }
    public String getName() { return deltitle.get(); }
    public void setname(String newname) { deltitle.set(newname); }

    public SimpleStringProperty descProperty() { return desctitle; }
    public String getdesc() { return desctitle.get(); }
    public void setdesc(String newname) { desctitle.set(newname); }


}
