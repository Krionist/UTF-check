package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TableView<Item> table;

    @FXML
    TableColumn folder_url_col;
    @FXML
    TableColumn folder_encode_col;
    @FXML
    TableColumn actions;

    // The table's data
    ObservableList<Item> data;
    @FXML
    private TextField folder_box;
    @FXML
    public void choseFolder(){

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if(selectedDirectory != null) {
            String folder_url=selectedDirectory.getAbsolutePath();
            folder_box.setText(folder_url);
            ArrayList<String> folder_list=getFileList(folder_url);
            folder_list.forEach(this::runFileEncoding);
        }
    }
    private ArrayList<String> getFileList(String folder_url){
        ArrayList<String> file_list=new ArrayList<String>();
        File folder = new File(folder_url);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                file_list.add(listOfFiles[i].getAbsolutePath());
            } else if (listOfFiles[i].isDirectory()) {
                file_list.addAll(getFileList(listOfFiles[i].getAbsolutePath()));
            }
        }
        return  file_list;
    }
    private void runFileEncoding(String file_url){
        try {
            FileInputStream   inputStream=new FileInputStream(file_url);
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            Item item=new Item();
            if(!inputStreamReader.getEncoding().equals("UTF8")){
                item.folder_url.set(file_url);
                item.encode.set(inputStreamReader.getEncoding());
                item.action.set("-");
                data.add(item);
                table.setItems(data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        folder_url_col.setCellValueFactory(
                new PropertyValueFactory<Item,String>("FolderUrl")
        );
        folder_encode_col.setCellValueFactory(
                new PropertyValueFactory<Item,String>("encode")
        );
        actions.setCellValueFactory(
                new PropertyValueFactory<Item,String>("action")
        );
        data= FXCollections.observableArrayList();
    }
}
