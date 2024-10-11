package com.dieu.lab1.controller;

import com.dieu.lab1.dto.AgentDto;
import com.dieu.lab1.service.IAgentService;
import com.dieu.lab1.service.impl.AgentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;

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

    private final IAgentService agentService;

    public ViewDetailController(IAgentService agentService) {
        this.agentService = new AgentService();
    }

    public void returnSearch(ActionEvent actionEvent) {
    }

    public void displayAgentDetail(int id){
        AgentDto agentDto = agentService.getAgent(id);
        txtFieldName.setText(agentDto.getName());
        txtFieldEmail.setText(agentDto.getEmail());
        txtFieldAddress.setText(agentDto.getAddress());
        txtFieldBalance.setText(agentDto.getAccountBalance().toString());
        txtFieldStatus.setText(agentDto.getStatus());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        txtFieldRegisterDate.setText(formatter.format(agentDto.getRegisterDate()));
    }
}
