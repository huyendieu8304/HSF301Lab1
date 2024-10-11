package com.dieu.lab1.controller;

import com.dieu.lab1.ApplicationSession;
import com.dieu.lab1.dto.AccountDto;
import com.dieu.lab1.entity.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeaderController {
    @FXML
    private ImageView imgView;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelToday;
    @FXML
    private Button btnLogout;

    private Stage stage;
    private final Image logo = new Image(getClass().getResourceAsStream("/com/dieu/lab1/assets/darwin.jpg"));

    public void initialize() {
        imgView.setImage(logo);
    }

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
        alert.setContentText("Bạn chắc chắn muốn thoát ứng dụng?");
        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) btnLogout.getScene().getWindow(); //the stage user are working on
            stage.close();
        }
    }
}
