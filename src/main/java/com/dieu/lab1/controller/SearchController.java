package com.dieu.lab1.controller;

import com.dieu.lab1.dto.AgentDto;
import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.enumeration.EAgentStatus;
import com.dieu.lab1.service.IAgentService;
import com.dieu.lab1.service.impl.AgentService;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchController {

    private IAgentService agentService;

    public SearchController() {
        this.agentService = new AgentService();
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
        tblColViewDetail.setCellFactory(col -> new TableCell<AgentDto, String>() {
            //create a link in a cell
            private final Hyperlink link = new Hyperlink("Xem Chi Tiết");
            //initialize block, run anytime the Hyperlink is create
            {
                // Set up the action when the hyperlink is clicked
                link.setOnAction(event -> {
                    //get the agent from the table, cause the information displyed in the next scene is same as in the table
                    //so, I just simply send it, no need to retrive from the db
                    AgentDto agent = getTableView().getItems().get(getIndex());
                    try {
                        // Load the new scene
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dieu/lab1/update.fxml"));
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

                        // Pass data
                        UpdateController updateController = loader.getController();
                        updateController.displayAgent(agent);

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

            //to make the link displayed
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(link);
                }
            }
        });

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
            tblView.setPlaceholder(new Label("Xin lỗi, không tìm thấy đại lí nào phù hợp."));
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
        labelDisplay.setText("Hiển thị trang " + currentPage + " trên " + noOfPages + " trang." );


    }

    public void updateNoOfPages() {
        String email = txtFieldEmail.getText().trim();
        String name = txtFieldName.getText().trim();
        String status = choiceBoxStatus.getSelectionModel().getSelectedItem();
        if (status.equals("All")) {
            status = null;
        }
        noOfPages = agentService.getNoPages(pageSize, status, email, name);
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
                    labelInvalidInputMessage.setText("Số lượng hàng từ 0 - 100");
                    return;
                }
            } catch (Exception e) {
                labelInvalidInputMessage.setText("Hãy nhập số nguyên dương");
                throw new RuntimeException(e);
            }
        }
        labelInvalidInputMessage.setText(""); //clear old message
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
    public void createNewAgent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dieu/lab1/create.fxml"));
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
