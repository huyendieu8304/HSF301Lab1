package com.dieu.lab1.controller;

import com.dieu.lab1.entity.Account;
import com.dieu.lab1.service.IAccountService;
import com.dieu.lab1.service.impl.AccountService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    private IAccountService accountService;

    public LoginController() {
        accountService = new AccountService();
    }

    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label labelError;

    public void verifyAccount(ActionEvent actionEvent) {
        if (validateInput(actionEvent)) {
            System.out.println("correct");
            Account account = accountService.findByEmail(txtFieldEmail.getText());
            if (account != null && account.getPassword().equals(txtFieldPassword.getText())) {
                System.out.println("correct account");
            }
        }

    }

    public boolean validateInput(ActionEvent actionEvent) {
        String email = txtFieldEmail.getText();
        String password = txtFieldPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            labelError.setText("Please enter a valid email address and password");
            return false;
        }
        return true;
    }
}
