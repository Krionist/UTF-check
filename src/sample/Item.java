package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Item  {
    public SimpleStringProperty folder_url = new SimpleStringProperty();
    public SimpleStringProperty encode = new SimpleStringProperty();
    public SimpleStringProperty action = new SimpleStringProperty();

    public String getFolderUrl() {
        return folder_url.get();
    }

    public String getAction() {
        return action.get();
    }

    public String getEncode() {
        return encode.get();
    }
}
