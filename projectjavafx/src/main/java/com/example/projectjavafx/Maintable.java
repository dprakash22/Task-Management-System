package com.example.projectjavafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Maintable {
    private final SimpleStringProperty startdatemain;
    private final SimpleStringProperty descmain;
    private  final SimpleStringProperty prioritymain;
    private  final SimpleStringProperty titlemain;

    public Maintable() {
        titlemain = new SimpleStringProperty(this, "titlemain");
        descmain = new SimpleStringProperty(this, "descmain");
        startdatemain = new SimpleStringProperty(this, "startdatemain");
        prioritymain = new SimpleStringProperty(this, "prioritymain");
    }


    public SimpleStringProperty titleProperty() { return titlemain; }
    public String getName() { return titlemain.get(); }
    public void setname(String newname) { titlemain.set(newname); }

    public SimpleStringProperty descProperty() { return descmain; }
    public String getdesc() { return descmain.get(); }
    public void setdesc(String newname) { descmain.set(newname); }

    public StringProperty startProperty() { return startdatemain; }
    public String getstdate() { return startdatemain.get(); }
    public void setsdate(String newName) { startdatemain.set(newName); }

    public StringProperty priorProperty() { return prioritymain; }
    public String getpriorvalue() { return prioritymain.get(); }

    public void setpriorvalue(String newName) { prioritymain.set(newName); }
}
