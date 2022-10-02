package controller;

import bo.BOFactory;
import bo.custom.ProgramBO;
import bo.custom.RegistrationDetailBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.RegistrationDetailsDAO;
import dto.ProgramDTO;
import dto.RegistrationDetailsDTO;
import dto.StudentDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ManageStudentsFormController {
    private final StudentBO studentBO=(StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENT);
    private final ProgramBO programBO=(ProgramBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.PROGRAM);
    private final RegistrationDetailBO registrationDetailBO= (RegistrationDetailBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.REGISTRATIONDETAIL);

    public AnchorPane studentContext;
    public Label lblDate;
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtStudentNames;
    public JFXComboBox<String> cmbProgramId;
    public JFXTextField txtDuration;
    public JFXTextField txtProgramFee;
    public JFXTextField txtProgramName;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNumber;
    public TableView<StudentTM> tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactNo;

    public void initialize() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));



        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        tblStudent.getColumns().setAll(colId,colName,colAddress,colContactNo);

        try {
            setStudentValueToTable();
            loadProgramIds();
            loadStudentIds();
        } catch (IOException e) {
            e.printStackTrace();
        }


        cmbProgramId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    if (newValue!=(null)){
                    try {
                        setProgramDetails(newValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}
                });
        cmbStudentId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    if (newValue!=(null)){
                    try {
                        setStudentDetails(newValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}
                });

    }

    private void setStudentDetails(String id) throws IOException {
        List<StudentDTO> all = studentBO.getAllStudents();
        for (StudentDTO studentDTO:all){
            if (id.equalsIgnoreCase(studentDTO.getId())){
                txtStudentNames.setText(studentDTO.getName());

            }
        }
    }
    private void setProgramDetails(String id) throws IOException {
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
    private void loadStudentIds() throws IOException {
        cmbStudentId.getItems().clear();
        List<StudentDTO> all=studentBO.getAllStudents();
        for (StudentDTO studentDTO:all){
            cmbStudentId.getItems().add(studentDTO.getId());
        }
    }

    private void setStudentValueToTable() throws IOException {

        List<StudentDTO> studentDTOS = studentBO.getAllStudents();
        for(StudentDTO studentDTO:studentDTOS){
            tblStudent.getItems().add(new StudentTM(studentDTO.getId(),
                    studentDTO.getName(),
                    studentDTO.getAddress(),
                    studentDTO.getContactNo()
            ));
        }

    }
    public void navigateToDashBoard(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/DashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.studentContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }



    public void saveStudentProgram(MouseEvent mouseEvent) {
        if (txtStudentNames.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Enter Student Details").show();
        }else {
            try {
                RegistrationDetailsDTO registrationDetailsDTO=new RegistrationDetailsDTO(
                        (String) cmbStudentId.getValue(),
                        (String) cmbProgramId.getValue(),
                        lblDate.getText());

                if (registrationDetailBO.addRegistration(registrationDetailsDTO)) {
                    new Alert(Alert.AlertType.CONFIRMATION,"SAVED !").show();
                    txtStudentNames.clear();
                    txtProgramName.clear();
                    txtDuration.clear();
                    txtProgramFee.clear();
                    cmbStudentId.getItems().clear();
                    loadStudentIds();
                    cmbProgramId.getItems().clear();
                    loadProgramIds();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void deleteStudent(MouseEvent mouseEvent) throws IOException {
        if (studentBO.deleteStudent(tblStudent.getSelectionModel().getSelectedItem().getId())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            tblStudent.getItems().clear();
            setStudentValueToTable();
            txtStudentId.clear();
            txtStudentName.clear();
            txtAddress.clear();
            txtContactNumber.clear();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void saveStudent(MouseEvent mouseEvent) {

        if (txtStudentId.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Enter Student Details").show();
        }else {
            try {
                StudentDTO studentDTO=new StudentDTO(
                        txtStudentId.getText(),
                        txtStudentName.getText(),
                        txtAddress.getText(),
                        txtContactNumber.getText()
                );

                if (studentBO.addStudent(studentDTO)){
                    new Alert(Alert.AlertType.CONFIRMATION,"SAVED !").show();
                    tblStudent.getItems().clear();
                    setStudentValueToTable();
                    txtStudentId.clear();
                    txtStudentName.clear();
                    txtAddress.clear();
                    txtContactNumber.clear();
                    cmbStudentId.getItems().clear();
                    loadStudentIds();
                }
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        }
    }

    public void updateStudent(MouseEvent mouseEvent) {
        try {
            if (studentBO.updateStudent(new StudentDTO(
                    txtStudentId.getText(),
                    txtStudentName.getText(),
                    txtAddress.getText(),
                    txtContactNumber.getText()
            ))){
                new Alert(Alert.AlertType.CONFIRMATION, "UPDATED !").show();
                txtStudentId.clear();
                setStudentValueToTable();
                txtAddress.clear();
                txtStudentName.clear();
                txtContactNumber.clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "ERROR").show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSelected(MouseEvent mouseEvent) {
        if (tblStudent.getSelectionModel().getSelectedItem()!=null){
            StudentTM studentTM=tblStudent.getSelectionModel().getSelectedItem();
            txtStudentId.setText(studentTM.getId());
            txtStudentName.setText(studentTM.getName());
            txtAddress.setText(studentTM.getAddress());
            txtContactNumber.setText(studentTM.getContactNo());
        }
    }
}
