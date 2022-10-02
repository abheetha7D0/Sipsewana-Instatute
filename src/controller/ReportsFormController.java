package controller;

import bo.BOFactory;
import bo.custom.ProgramBO;
import bo.custom.ReportBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import entity.CustomEntity;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportsFormController {
    private final ProgramBO programBO=(ProgramBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.PROGRAM);
private final ReportBO reportBO=(ReportBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.REPORT);


    public AnchorPane reportContext;
    public Label lblDate;
    public TableView<CustomEntity> tblReports;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public JFXComboBox<String> cmbProgramId;
    public JFXTextField txtDuration;
    public JFXTextField txtProgramFee;
    public JFXTextField txtProgramName;

    public void initialize() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {

            loadProgramIds();

        } catch (IOException e) {
            e.printStackTrace();
        }
        cmbProgramId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    if (newValue!=(null)){
                        try {
                            setProgramDetail(newValue);
                            setProgramDetailsToTable(newValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    public void navigateToDashBoard(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/DashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.reportContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }


    private void setProgramDetail(String id) throws IOException {
        List<ProgramDTO> all = programBO.getAllPrograms();
        for (ProgramDTO programDTO:all){
            if (id.equalsIgnoreCase(programDTO.getId())){
                txtProgramName.setText(programDTO.getName());
                txtDuration.setText(programDTO.getDuration());
                txtProgramFee.setText(programDTO.getFee());
            }
        }
    }
    private void loadProgramIds() throws IOException {
        cmbProgramId.getItems().clear();
        List<ProgramDTO> all = programBO.getAllPrograms();
        for (ProgramDTO programDTO:all){
            cmbProgramId.getItems().add(programDTO.getId());
        }
    }

    private void setProgramDetailsToTable(String id) throws Exception {
        ObservableList<CustomEntity> temp = FXCollections.observableArrayList();
        for (CustomEntity customEntity:reportBO.getAllDetails(id)) {
            temp.add(customEntity);
            tblReports.setItems(temp);
        }
    }
}
