package com.dieu.lab1.controller;

import com.dieu.lab1.dto.AgentDto;
import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.service.IAgentService;
import com.dieu.lab1.service.impl.AgentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class ViewDetailController {

    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldAddress;
    @FXML
    private TextField txtFieldBalance;
    @FXML
    private TextField txtFieldStatus;
    @FXML
    private TextField txtFieldRegisterDate;
    @FXML
    private Button btnReturn;
    @FXML
    private Button btnUpdate;

    private static AgentDto displayedAgent;

    public void displayAgent(AgentDto agentDto) {
        displayedAgent = agentDto;
        txtFieldName.setText(agentDto.getName());
        txtFieldEmail.setText(agentDto.getEmail());
        txtFieldAddress.setText(agentDto.getAddress());
        txtFieldBalance.setText(agentDto.getAccountBalance().toString());
        txtFieldStatus.setText(agentDto.getStatus());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtFieldRegisterDate.setText(agentDto.getRegisterDate().format(formatter));
    }

    //switch to update scene
    public void updateAgent(ActionEvent actionEvent)  throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dieu/lab1/update.fxml"));
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

        UpdateController updateController = loader.getController();
//        updateController.displayAgent(displayedAgent.getId());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    //return to search scene
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
