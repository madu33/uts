package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Activity;
import model.Student;
import model.Subject;
import model.University;

import javax.sound.sampled.Control;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController extends Controller implements Initializable {

    Student student;
    University university;
    ObservableList<Subject>subjects;

    @FXML
    private TableView<Activity> tbSubject;

    @FXML
    private TableColumn<Activity, Integer> tbSubjectActivity;

    @FXML
    private TableColumn<Activity, Integer> tbSubjectCapacity;

    @FXML
    private TableColumn<Activity, String> tbSubjectDay;

    @FXML
    private TableColumn<Activity, Integer> tbSubjectDuration;

    @FXML
    private TableColumn<Activity, IntegerProperty> tbSubjectEnrolled;

    @FXML
    private TableColumn<Activity, String> tbSubjectGroup;

    @FXML
    private TableColumn<Activity, String> tbSubjectRoom;

    @FXML
    private TableColumn<Activity, Integer> tbSubjectStart;

    @FXML
    private TableColumn<Activity, Integer> tbSubjectSubject;


    @FXML
    private ComboBox<String> comSubject;

    @FXML
    private Label lbHeaderName;

    @FXML
    private Label stuAttendance;

    @FXML
    private Label stuNumber;

    @FXML
    private Label stuScholarship;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        student=(Student)model;
        university = University.getInstance();
        loadDataIntoHeader();
        loadDataIntoComboBox();
        loadDataIntoSubjectTable();

    }

    private void loadDataIntoHeader() {
        lbHeaderName.setText("Logged in as "+student.getName());
        stuNumber.setText(student.getNumber());
        stuAttendance.setText(student.getAttendance());
        stuScholarship.setText(student.getScholarship()+"");
    }

    private void loadDataIntoComboBox() {
        subjects = university.getSubjects();
        ObservableList<String> currentList = FXCollections.observableArrayList();
        for (Subject sub:subjects){
             currentList.add(sub.toString());
        }
        comSubject.setItems(currentList);

    }

    private void loadDataIntoSubjectTable() {

    }

    @FXML
    void chooseSubject(ActionEvent event) {
        int selectedIndex = comSubject.getSelectionModel().getSelectedIndex();
        ObservableList<Activity> activities = subjects.get(selectedIndex).getActivities();

        tbSubjectSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        tbSubjectGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        tbSubjectActivity.setCellValueFactory(new PropertyValueFactory<>("number"));
        tbSubjectDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        tbSubjectStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        tbSubjectDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tbSubjectRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        tbSubjectCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        tbSubjectEnrolled.setCellValueFactory(new PropertyValueFactory<>("enrolled"));
        tbSubject.setItems(activities);

    }
}
