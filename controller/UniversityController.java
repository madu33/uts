package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Student;
import model.University;


import java.net.URL;
import java.util.ResourceBundle;

public class UniversityController extends Controller implements Initializable {


    University university;

    ObservableList<Student> students;

    @FXML
    private TableColumn<Student, String> tbName;

    @FXML
    private TableColumn<Student, String> tbNumber;

    @FXML
    private TableView<Student> tbStudent;

    @FXML
    private Button btnAddStudent;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRemoveStudent;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        university=University.getInstance();
        btnRemoveStudent.setDisable(true);
        btnLogin.setDisable(true);
        students = university.getStudents();

        loadStudentData(); // load student data from University Model class

    }

    @FXML
    void selectStudent(MouseEvent event) {
        Student student = tbStudent.getSelectionModel().getSelectedItem();
        try {
            if(!student.getName().isEmpty()){
                btnRemoveStudent.setDisable(false);
                btnLogin.setDisable(false);
            }
        }catch (NullPointerException e){
            System.out.println("No Student Selected");
        }
    }

    @FXML
    void login(ActionEvent event) {
        try {
            ViewLoader.showStage(new Student(), "/view/student.fxml", "Student", new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AddNewStudent(MouseEvent event) {
        try {
            ViewLoader.showStage(university, "/view/add_student.fxml", "Add New Student", new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadStudentData() {
        tbNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        tbName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbStudent.setItems(this.students);
    }

    @FXML
    void btnRemoveStudent(ActionEvent event) {
        Student student = tbStudent.getSelectionModel().getSelectedItem();
        university.remove(student);
    }


}
