package com.dieu.lab1;

import com.dieu.lab1.entity.Account;
import com.dieu.lab1.repository.IAccountRepository;
import com.dieu.lab1.repository.impl.AccountRepository;
import com.dieu.lab1.service.IAccountService;
import com.dieu.lab1.service.impl.AccountService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static IAccountService accountService;

    public static IAccountService getAccountService() {
        return accountService;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("create.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());

        BorderPane root = fxmlLoader.load();

//        FXMLLoader headerLoader = new FXMLLoader(Application.class.getResource("/com/dieu/lab1/components/header.fxml"));
//        Parent header = headerLoader.load();
//
//        FXMLLoader footerLoader = new FXMLLoader(Application.class.getResource("/com/dieu/lab1/components/footer.fxml"));
//        Parent footer = footerLoader.load();
//
//        root.setTop(header);
//        root.setBottom(footer);

        Scene scene = new Scene(root);

        stage.setTitle("Agent management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}