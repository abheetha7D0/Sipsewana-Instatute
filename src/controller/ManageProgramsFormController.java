package controller;

import bo.BOFactory;
import bo.custom.ProgramBO;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.tm.ProgramTM;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ManageProgramsFormController {

    private final ProgramBO programBO=(ProgramBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.PROGRAM);

    public AnchorPane programContext;
    public Label lblDate;
    public TableView<ProgramTM> tblPrograms;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colDuration;
    public TableColumn colProgramFee;
    public JFXTextField txtProgramId;
    public JFXTextField txtProgramName;
    public JFXTextField txtDuration;
    public JFXTextField txtProgramFee;

    public void initialize() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        tblPrograms.getColumns().setAll(colId,colName,colDuration,colProgramFee);

        try {
            setProgramsValueToTable();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getSelected(MouseEvent mouseEvent) {
        if (tblPrograms.getSelectionModel().getSelectedItem()!=null){
            ProgramTM programTM=tblPrograms.getSelectionModel().getSelectedItem();
            txtProgramId.setText(programTM.getId());
            txtProgramName.setText(programTM.getName());
            txtDuration.setText(programTM.getDuration());
            txtProgramFee.setText(programTM.getFee());
        }
    }

    private void setProgramsValueToTable() throws IOException {

        List<ProgramDTO> programDTOS = programBO.getAllPrograms();
        for(ProgramDTO programDTO:programDTOS){
            tblPrograms.getItems().add(new ProgramTM(programDTO.getId(),
                    programDTO.getName(),
                    programDTO.getDuration(),
                    programDTO.getFee()
            ));
        }

    }
    public void navigateToDashBoard(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/DashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.programContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }


    public void saveProgram(MouseEvent mouseEvent) {
        if (txtProgramId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Insert Data").show();
        } else {
            ProgramDTO programDTO = new ProgramDTO(
                    txtProgramId.getText(),
                    txtProgramName.getText(),
                    txtDuration.getText(),
                    txtProgramFee.getText()
            );
            try {
                if (programBO.addProgram(programDTO)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
                    tblPrograms.getItems().clear();

                    setProgramsValueToTable();
                    txtProgramId.clear();
                    txtProgramName.clear();
                    txtDuration.clear();
                    txtProgramFee.clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "ERROR").show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void deleteProgram(MouseEvent mouseEvent) throws IOException {
        if (programBO.deleteProgram(tblPrograms.getSelectionModel().getSelectedItem().getId())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            tblPrograms.getItems().clear();
            setProgramsValueToTable();
            txtProgramId.clear();
            txtProgramName.clear();
            txtDuration.clear();
            txtProgramFee.clear();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void updateProgram(MouseEvent mouseEvent) {
        try {
            if (programBO.updateProgram(new ProgramDTO(
                    txtProgramId.getText(),
                    txtProgramName.getText(),
                    txtDuration.getText(),
                    txtProgramFee.getText()
            ))){
                new Alert(Alert.AlertType.CONFIRMATION, "UPDATED !").show();
                tblPrograms.getItems().clear();
                setProgramsValueToTable();
                txtProgramId.clear();
                txtProgramName.clear();
                txtDuration.clear();
                txtProgramFee.clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "ERROR").show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
