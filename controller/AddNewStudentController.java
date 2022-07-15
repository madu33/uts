package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.University;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewStudentController extends Controller implements Initializable {

    ToggleGroup toggleGroup = new ToggleGroup();
    String attendance;
    boolean scholarship=false;
    University university;
    boolean isNumberOk,isNameOk,isAttendanceOk=false;

    @FXML
    private Button btnAddStudent;

    @FXML
    private Label lbError;

    @FXML
    private Button btnClose;

    @FXML
    private CheckBox checkScholarship;

    @FXML
    private AnchorPane lbAddNewStudent;

    @FXML
    private RadioButton rdFullTime;

    @FXML
    private RadioButton rdPartTime;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtStudentNumber;

    @FXML
    void setAttendanceFullTime(ActionEvent event) {
        attendance="ft";
        isAttendanceOk=true;
        enableAddButton();
    }

    @FXML
    void setAttendancePartTime(ActionEvent event) {
        attendance="pt";
        isAttendanceOk=true;
        enableAddButton();
    }

    @FXML
    void addNewStudent(ActionEvent event) {
        String stuNumber=txtStudentNumber.getText();
        String stuName=txtStudentName.getText();
        System.out.println(scholarship);
        try {
            university.addStudent(stuNumber,stuName,attendance,scholarship);
            Stage stage = (Stage) btnAddStudent.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            lbError.setText("Student already exists");
        }
    }

    @FXML
    void isStudentNumber(ActionEvent event) {

    }

    @FXML
    void checkStudentNumberHere(KeyEvent event) {
        String text = txtStudentNumber.getText();
        if(text.length()>0){
            isNumberOk=true;
        }else{
            isNumberOk=false;
        }
        enableAddButton();
    }
    @FXML
    void checkStudentNameHere(KeyEvent event) {
        String text = txtStudentName.getText();
        if(text.length()>0){
            isNameOk=true;
        }else{
            isNameOk=false;
        }
        enableAddButton();
    }

    void enableAddButton(){
        if(isNumberOk && isNameOk && isAttendanceOk){
            btnAddStudent.setDisable(false);
        }

    }

    @FXML
    void checkScholarship(ActionEvent event) {
        scholarship=checkScholarship.isSelected();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rdFullTime.setToggleGroup(toggleGroup);
        rdPartTime.setToggleGroup(toggleGroup);
        university=University.getInstance();
        btnAddStudent.setDisable(true);
    }



}
