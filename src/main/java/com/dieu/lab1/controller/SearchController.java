package com.dieu.lab1.controller;

import com.dieu.lab1.dto.AgentDto;
import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.enumeration.EAgentStatus;
import com.dieu.lab1.service.IAgentService;
import com.dieu.lab1.service.impl.AgentService;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchController {

    private IAgentService agentService;

    public SearchController() {
        agentService = new AgentService();
    }

    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldPageSize;

    @FXML
    private ChoiceBox<String> choiceBoxStatus;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnPrevious;
    @FXML
    private Button btnNext;
    @FXML
    private Label labelDisplay;
    @FXML
    private Label labelInvalidInputMessage;

    @FXML
    private TableView<AgentDto> tblView;
    @FXML
    private TableColumn<AgentDto, String> tblColOrder;
    @FXML
    private TableColumn<AgentDto, String> tblColEmail;
    @FXML
    private TableColumn<AgentDto, String> tblColName;
    @FXML
    private TableColumn<AgentDto, String> tblColAddress;
    @FXML
    private TableColumn<AgentDto, String> tblColStatus;
    @FXML
    private TableColumn<AgentDto, LocalDate> tblColRegisterDate;
    @FXML
    private TableColumn<AgentDto, Double> tblColAccontBalance;
    @FXML
    private TableColumn<AgentDto, String> tblColViewDetail;


    private static int currentPage = 1;
    private static int pageSize = 10;
    private static int noOfPages;


    public void initialize() {

        //set up choice box
        List<String> statusOptions = new ArrayList<>();
        statusOptions.add("All");
        statusOptions.addAll(Arrays.stream(EAgentStatus.values())
                .map(EAgentStatus::toString)
                .toList());
        choiceBoxStatus.getItems().addAll(statusOptions);
        choiceBoxStatus.getSelectionModel().select(1);

        //setup data for the table
        tblColOrder.setCellValueFactory(cellData -> {
            int index = tblView.getItems().indexOf(cellData.getValue());
            return new ReadOnlyStringWrapper(String.valueOf((currentPage-1) * pageSize + index + 1));
        });
        tblColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblColRegisterDate.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        tblColAccontBalance.setCellValueFactory(new PropertyValueFactory<>("accountBalance"));

        //for more detail column
        //tblColViewDetail.setCellValueFactory(new PropertyValueFactory<>("viewDetail"));

        //add data to the table
        updateNoOfPages();
        updateTableData();


    }

    public void updateTableData() {
        //remove old data
        tblView.getItems().clear();

        String email = txtFieldEmail.getText().trim();
        String name = txtFieldName.getText().trim();
        String status = choiceBoxStatus.getSelectionModel().getSelectedItem();
        if (status.equals("All")) {
            status = null;
        }

        List<AgentDto> agentDtoList = agentService.searchAgent(pageSize, currentPage, status, email, name);
        if (!agentDtoList.isEmpty()) {
            tblView.getItems().addAll(agentDtoList);
        } else {
            tblView.setPlaceholder(new Label("Sorry, we can't find any agent matching your criteria"));
        }

        //set status of previous button
        if (currentPage < 2) {
            btnPrevious.setDisable(true);
        } else {
            btnPrevious.setDisable(false);
        }
        if (currentPage == noOfPages) {
            btnNext.setDisable(true);
        } else {
            btnNext.setDisable(false);
        }

        //update labelDisplay in the end
//        labelDisplay.setText("Hiển thị từ " + (currentPage - 1) * pageSize + " đến " + no );


    }

    public void updateNoOfPages() {
        String email = txtFieldEmail.getText().trim();
        String name = txtFieldName.getText().trim();
        String status = choiceBoxStatus.getSelectionModel().getSelectedItem();
        if (status.equals("All")) {
            status = null;
        }
        noOfPages = agentService.getNoPages(pageSize, status, email, name);
        System.out.println(noOfPages);
    }

    public void searchAgent(ActionEvent actionEvent) {
        currentPage = 1;
        updateNoOfPages();
        updateTableData();
    }


    public void changePageSize(ActionEvent actionEvent) {
        if (!txtFieldPageSize.getText().isEmpty()) {
            try {
                pageSize = Integer.parseInt(txtFieldPageSize.getText());
                if (pageSize <= 0 || pageSize > 100) {
                    labelInvalidInputMessage.setText("the page size must be between 1 and 100");
                    return;
                }
            } catch (Exception e) {
                labelInvalidInputMessage.setText("Please enter a valid page size");
                throw new RuntimeException(e);
            }
        }
        currentPage = 1;
        updateNoOfPages();
        updateTableData();
    }

    public void previousPage(ActionEvent actionEvent) {
        if (currentPage > 1) {
            currentPage--;
            updateTableData();
        }
    }

    public void nextPage(ActionEvent actionEvent) {
        if (currentPage < noOfPages) {
            currentPage++;
            updateTableData();
        }
    }

    //direct user to another page
    public void createNewAgent(ActionEvent actionEvent) {



    }


}
