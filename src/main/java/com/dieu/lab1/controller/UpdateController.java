package com.dieu.lab1.controller;

import com.dieu.lab1.dto.AgentDto;
import com.dieu.lab1.enumeration.EAgentStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.Arrays;

public class UpdateController {
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldAddress;
    @FXML
    private TextField txtFieldBalance;
    @FXML
    private DatePicker datePickerRegisterDate;
    @FXML
    private ChoiceBox choiceBoxStatus;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReturn;

    public void initialize(AgentDto agent) {

        txtFieldName.setText(agent.getName());
        txtFieldEmail.setText(agent.getEmail());
        txtFieldAddress.setText(agent.getAddress());

        //initialize value for choiceBox
        String[] agentStatus = Arrays.stream(EAgentStatus.values())
                .map(EAgentStatus::toString)
                .toArray(String[]::new);
        choiceBoxStatus.getItems().addAll(agentStatus);
        //set the choice same with the current status
        String agentCurrentStatus = agent.getStatus();
        int defaultIndex = Arrays.asList(agentStatus).indexOf(agentCurrentStatus);
        if (defaultIndex >= 0) {
            choiceBoxStatus.getSelectionModel().select(defaultIndex);
        } else {
            //the status doesn't match any option
            //can't happen but add for in case
            choiceBoxStatus.getSelectionModel().select(0);
        }
        txtFieldBalance.setText(agent.getAccountBalance().toString());
        datePickerRegisterDate.setValue(agent.getRegisterDate());
    }

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
