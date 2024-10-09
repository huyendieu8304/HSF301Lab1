package com.dieu.lab1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeaderController {

    @FXML
    public Label labelEmail;
    @FXML
    public Label labelToday;
    @FXML
    public Button btnLogout;

    public void displayUser(String email){
        labelEmail.setText(email);
    }

    public void displayToday(){
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        labelToday.setText(formatter.format(date));
    }
}
