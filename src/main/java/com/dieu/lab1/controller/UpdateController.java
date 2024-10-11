package com.dieu.lab1.controller;

import com.dieu.lab1.dto.AgentDto;
import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.enumeration.EAgentStatus;
import com.dieu.lab1.service.IAgentService;
import com.dieu.lab1.service.impl.AgentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private ChoiceBox<String> choiceBoxStatus;
    @FXML
    private Label labelMessage;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReturn;

    private IAgentService agentService;

    public UpdateController() {
        this.agentService = new AgentService();
    }

    private static int agentId;

    public void displayAgent(AgentDto agentDto) {
        agentId = agentDto.getId();
        txtFieldName.setText(agentDto.getName());
        txtFieldEmail.setText(agentDto.getEmail());
        txtFieldAddress.setText(agentDto.getAddress());

        //initialize value for choiceBox
        String[] agentStatus = Arrays.stream(EAgentStatus.values())
                .map(EAgentStatus::toString)
                .toArray(String[]::new);
        choiceBoxStatus.getItems().addAll(agentStatus);
        //set the choice same with the current status
        String agentCurrentStatus = agentDto.getStatus();
        int defaultIndex = Arrays.asList(agentStatus).indexOf(agentCurrentStatus);
        if (defaultIndex >= 0) {
            choiceBoxStatus.getSelectionModel().select(defaultIndex);
        } else {
            //the status doesn't match any option
            //can't happen but add for in case
            choiceBoxStatus.getSelectionModel().select(0);
        }
        txtFieldBalance.setText(agentDto.getAccountBalance().toString());
        datePickerRegisterDate.setValue(agentDto.getRegisterDate());

    }

    public void updateAgent(ActionEvent actionEvent) {
        if (getInput(actionEvent) != null){
            if (agentService.updateAgent(getInput(actionEvent))){
                labelMessage.setText("Thông tin đại lí đã được cập nhật.");

            } else {
                labelMessage.setText("Cập nhật thông tin đại lí thất bại.");
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
            labelMessage.setText("Hãy điền đủ thông tin.");
            return null;
        }

        Agent agent = new Agent();

        //validate agent's name
        if (!txtFieldName.getText().matches("[a-zA-Z\\s]+")){
            labelMessage.setText("Tên đại lí chỉ chứa chữ cái và khoảng trắng.");
            return null;
        }
//        if (agentService.isAgentNameExist(txtFieldName.getText())){
//            labelMessage.setText("Tên đại lí đã tồn tại.");
//            return null;
//        }
        agent.setName(txtFieldName.getText());

        //validate email
        if (!txtFieldEmail.getText().matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.([a-zA-Z]{2,}$)+")){
            labelMessage.setText("Địa chỉ email không hợp lệ.");
            return null;
        }
//        if (agentService.isAgentEmailExist(txtFieldEmail.getText())){
//            labelMessage.setText("Địa chỉ email đại lí đã tồn tại.");
//            return null;
//        }
        agent.setEmail(txtFieldEmail.getText());

        //validate accont's balance
        try {
            Double balance = Double.parseDouble(txtFieldBalance.getText());
            if (balance < 0){
                labelMessage.setText("Số dư không được âm.");
                return null;
            }
            agent.setAccountBalance(balance);
        }catch (NumberFormatException e){
            labelMessage.setText("Số dư phải là số dương.");
            e.printStackTrace();
            return null;
        }

        agent.setAddress(txtFieldAddress.getText());
        agent.setStatus(EAgentStatus.valueOf(choiceBoxStatus.getSelectionModel().getSelectedItem().toString()));
        agent.setRegisterDate(datePickerRegisterDate.getValue());
        agent.setId(agentId);
        return agent;
    }

    public void returnSearch(ActionEvent actionEvent) throws IOException {
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
