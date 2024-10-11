package com.dieu.lab1.controller;

import com.dieu.lab1.ApplicationSession;
import com.dieu.lab1.dto.AccountDto;
import com.dieu.lab1.entity.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeaderController {

    @FXML
    public Label labelEmail;
    @FXML
    public Label labelToday;
    @FXML
    public Button btnLogout;

    Stage stage;

    public void displayUser(){
        AccountDto account = (AccountDto) ApplicationSession.getAttribute("account");
        labelEmail.setText(account.getEmail());
    }

    public void displayToday(){
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        labelToday.setText(formatter.format(date));
    }

    public void logOut(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Đăng xuất");
        alert.setHeaderText("Xác nhận đăng xuất");
        alert.setContentText("Bạn sắp thoát chương trình");
        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) btnLogout.getScene().getWindow(); //the stage user are working on
            stage.close();
        }
    }
}
