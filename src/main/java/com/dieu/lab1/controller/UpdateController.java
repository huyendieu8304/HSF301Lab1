package com.dieu.lab1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateController {
    public TextField txtFieldName;
    public TextField txtFieldEmail;
    public TextField txtFieldAddress;
    public TextField txtFieldBalance;
    public DatePicker datePickerRegisterDate;
    public ChoiceBox choiceBoxStatus;
    public Button btnSave;
    public Button btnReturn;


    public void updateAgent(ActionEvent actionEvent) {
    }

    public void returnSearch(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dieu/lab1/search.fxml"));
        BorderPane root = loader.load();

//        FXMLLoader headerLoader = new FXMLLoader(getClass().getResource("/com/dieu/lab1/components/header.fxml"));
//        Parent header = headerLoader.load();
//
//        FXMLLoader footerLoader = new FXMLLoader(getClass().getResource("/com/dieu/lab1/components/footer.fxml"));
//        Parent footer = footerLoader.load();
//
//        root.setTop(header);
//        root.setBottom(footer);
//
//        HeaderController headerController = headerLoader.getController();
//        headerController.displayUser();
//        headerController.displayToday();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
