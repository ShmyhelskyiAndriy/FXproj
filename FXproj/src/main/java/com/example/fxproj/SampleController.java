package com.example.fxproj;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URISyntaxException;

public class SampleController {

    @FXML
    private TableColumn<Person, String> columnPIP;
    @FXML
    private TableColumn<Person, String> columnPhone;
    @FXML
    private Button addButton;
    @FXML
    private Button otherLabsButton;
    @FXML
    private Label labelCount;
    @FXML
    private TableView tableAddressBook;

    private Stage newStage;
    private Stage editDialogStage;
    private Parent root;
    private FXMLLoader fxmlLoaders = new FXMLLoader();

    private EditController editController;



    CollectionAddressBook collectionAdressBook = new CollectionAddressBook();

    @FXML
    private Button delButton;

    @FXML
    public void initialize(){
        otherLabsButton.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("otherLabs.fxml"));
            Stage stage = new Stage();
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 600);
            }catch (IOException e){
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Інші практичні");
            stage.setResizable(false);
            stage.show();

        });

        try{
            fxmlLoaders.setLocation(SampleController.class.getResource("edit.fxml"));
            root = fxmlLoaders.load();
            editController = fxmlLoaders.getController();
        }catch (IOException e){
            e.printStackTrace();
        }

        collectionAdressBook.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });


        columnPIP.setCellValueFactory(new PropertyValueFactory<Person,String>("pip"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person,String>("phone"));



        tableAddressBook.setItems(collectionAdressBook.getPersonList());
        collectionAdressBook.testData();
    }

    @FXML
    void openWindow(ActionEvent event) throws IOException, URISyntaxException {
//        showDialog();
        Button clickedButton = (Button) event.getSource();
        switch (clickedButton.getId()){
            case "btnAdd":
                editController.setPerson(new Person());
                showDialog();
                collectionAdressBook.add(editController.getPerson());
                break;
            case "btnEdit":
                editController.setPerson((Person)
                        tableAddressBook.getSelectionModel().getSelectedItem());
                showDialog();
                break;
            case "btnDelete":
                collectionAdressBook.delete((Person)
                        tableAddressBook.getSelectionModel().getSelectedItem());
                break;
        }
    }

    public void showDialog(){
        editDialogStage = new Stage();
        editDialogStage.setScene(new Scene(root));
        editDialogStage.setTitle("Вікно Редагування");

        editDialogStage.setResizable(false);
        editDialogStage.initModality(Modality.WINDOW_MODAL);
        editDialogStage.initOwner(newStage);

        editDialogStage.showAndWait();
    }


    @FXML
    void onAddButtonClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(SampleController.class.getResource("edit.fxml"));


        try{
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            stage.setScene(scene);

            stage.setTitle("edit");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(addButton.getScene().getWindow());
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    private void updateCountLabel(){
        labelCount.setText("Кількість записів: " + collectionAdressBook.getPersonList().size());
    }


    @FXML
    void new_Alert(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Видалення");

        alert.setHeaderText("Results:");
        alert.setContentText("Ви успішно видалили запис! ");

        alert.showAndWait();

    }

    public void setNewStage(Stage newStage){
        this.newStage = newStage;

    }

    @FXML
    void otherLabsButton(){
        FXMLLoader fxmlLoader = new FXMLLoader(SampleController.class.getResource("otherLabs.fxml"));


        try{
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);

            stage.setTitle("OtherLabs");
            stage.setResizable(false);

            stage.initOwner(addButton.getScene().getWindow());
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}













