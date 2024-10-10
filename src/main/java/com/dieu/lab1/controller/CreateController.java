package com.dieu.lab1.controller;

import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.enumeration.EAgentStatus;
import com.dieu.lab1.service.IAgentService;
import com.dieu.lab1.service.impl.AgentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;

public class CreateController {

    private IAgentService agentService;

    public CreateController() {
        agentService = new AgentService();
    }

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
    private Label labelMessage;


    public void initialize() {

        //initialize value for choiceBox
        String[] agentStatus = Arrays.stream(EAgentStatus.values())
                .map(EAgentStatus::toString)
                .toArray(String[]::new);
        choiceBoxStatus.getItems().addAll(agentStatus);
        choiceBoxStatus.getSelectionModel().select(0);
    }

    public void createAgent(ActionEvent actionEvent) {
        if (getInput(actionEvent) != null){
            if (agentService.createAgent(getInput(actionEvent))){
                labelMessage.setText("Agent created");

            } else {
                labelMessage.setText("Agent creation failed");
            }
        }

    }

    public Agent getInput (ActionEvent actionEvent) {

        //has to input all text field
        if (txtFieldName.getText().trim().isEmpty()
                || txtFieldEmail.getText().trim().isEmpty()
                || txtFieldAddress.getText().trim().isEmpty()
                || txtFieldBalance.getText().trim().isEmpty()
                || datePickerRegisterDate.getValue() == null) {
            labelMessage.setText("Please fill all the fields");
            return null;
        }

        Agent agent = new Agent();

        //validate agent's name
        if (!txtFieldName.getText().matches("[a-zA-Z\\s]+")){
            labelMessage.setText("Agent name must contain only letters and spaces");
            return null;
        }
        agent.setName(txtFieldName.getText());

        //validate email
        if (!txtFieldEmail.getText().matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.([a-zA-Z]{2,}$)+")){
            labelMessage.setText("Email is invalid");
            return null;
        }
        agent.setEmail(txtFieldEmail.getText());

        //validate accont's balance
        try {
            Double balance = Double.parseDouble(txtFieldBalance.getText());
            if (balance < 0){
                labelMessage.setText("Balance cannot be negative");
                return null;
            }
            agent.setAccountBalance(balance);
        }catch (NumberFormatException e){
            labelMessage.setText("Balance cannot contain alphabet characters");
            e.printStackTrace();
            return null;
        }

        agent.setAddress(txtFieldAddress.getText());
        agent.setStatus(EAgentStatus.valueOf(choiceBoxStatus.getSelectionModel().getSelectedItem().toString()));
        agent.setRegisterDate(datePickerRegisterDate.getValue());

        return agent;
    }
}
