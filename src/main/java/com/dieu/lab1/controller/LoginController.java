package com.dieu.lab1.controller;

import com.dieu.lab1.Application;
import com.dieu.lab1.ApplicationSession;
import com.dieu.lab1.dto.AccountDto;
import com.dieu.lab1.entity.Account;
import com.dieu.lab1.service.IAccountService;
import com.dieu.lab1.service.impl.AccountService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void verifyAccount(ActionEvent actionEvent) throws IOException {
        AccountDto account = validateInputAccount(actionEvent);
        //account is right
        if (account != null && accountService.verifyAccount(account)) {
            ApplicationSession.addAttribute("account", account);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dieu/lab1/search.fxml"));
            BorderPane root = loader.load();

            FXMLLoader headerLoader = new FXMLLoader(getClass().getResource("/com/dieu/lab1/components/header.fxml"));
            Parent header = headerLoader.load();

            FXMLLoader footerLoader = new FXMLLoader(getClass().getResource("/com/dieu/lab1/components/footer.fxml"));
            Parent footer = footerLoader.load();

            root.setTop(header);
            root.setBottom(footer);

            HeaderController headerController = headerLoader.getController();
            headerController.displayUser();
            headerController.displayToday();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public AccountDto validateInputAccount(ActionEvent actionEvent) {
        String email = txtFieldEmail.getText();
        String password = txtFieldPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            labelError.setText("Hãy nhập email và password");
            return null;
        }

        if (!email.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.([a-zA-Z]{2,}$)+")) {
            labelError.setText("Địa chỉ email không hợp lệ");
            return null;
        }

        AccountDto account = new AccountDto();
        account.setEmail(email);
        account.setPassword(password);
        return account;
    }
}
