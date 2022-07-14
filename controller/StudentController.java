package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    private ObservableList<Activity> myActivity = FXCollections.observableArrayList();
    Activity selectSubject;
    Activity selectActivity;

    @FXML
    private Button btnEnroll;

    @FXML
    private Button btnWithdraw;

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

//    ---------------------------Activity Table -------------------------------------

    @FXML
    private TableView<Activity> tbActivity;

    @FXML
    private TableColumn<Activity, Integer> tbActivityActivity;

    @FXML
    private TableColumn<Activity, Integer> tbActivityCapacity;

    @FXML
    private TableColumn<Activity, String> tbActivityDay;

    @FXML
    private TableColumn<Activity, Integer> tbActivityDuration;

    @FXML
    private TableColumn<Activity, Integer> tbActivityEnrolled;

    @FXML
    private TableColumn<Activity, String> tbActivityGroup;

    @FXML
    private TableColumn<Activity, String> tbActivityRoom;

    @FXML
    private TableColumn<Activity, Integer> tbActivityStart;

    @FXML
    private TableColumn<Activity, Subject> tbActivitySubject;

    @FXML
    private ComboBox<String> comSubject;

    @FXML
    private Label lbHeaderName;

    @FXML
    private Button btnLogout;

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
        buttonDisable();
        loadDataIntoHeader();
        loadDataIntoComboBox();
        loadDataIntoMyActivityTable();
    }

    private void buttonDisable() {
        btnEnroll.setDisable(true);
        btnWithdraw.setDisable(true);
    }

    @FXML
    void btnLogout(ActionEvent event) {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }

    private void loadDataIntoHeader() {
        lbHeaderName.setText("Logged in as "+student.getName());
        stuNumber.setText(student.getNumber());
        stuAttendance.setText(student.getAttendance());
        String text="";
        if(student.getScholarship()){
             text="Yes";
        }else{
             text="No";
        }
        stuScholarship.setText(text);
    }

    private void loadDataIntoComboBox() {
        subjects = university.getSubjects();
        ObservableList<String> currentList = FXCollections.observableArrayList();
        for (Subject sub:subjects){
             currentList.add(sub.toString());
        }
        comSubject.setItems(currentList);

    }

    private void loadDataIntoMyActivityTable() {
        ObservableList<Activity> activities = student.getActivities();
        if(activities.size() !=0){
            tbActivitySubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            tbActivityGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
            tbActivityActivity.setCellValueFactory(new PropertyValueFactory<>("number"));
            tbActivityDay.setCellValueFactory(new PropertyValueFactory<>("day"));
            tbActivityStart.setCellValueFactory(new PropertyValueFactory<>("start"));
            tbActivityDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            tbActivityRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
            tbActivityCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
            tbActivityEnrolled.setCellValueFactory(new PropertyValueFactory<>("enrolled"));
            tbActivity.setItems(activities);
        }else{
            tbActivity.setPlaceholder(new Label("Not enrolled in any activities"));

        }

    }

    @FXML
    void btnEnroll(ActionEvent event) {
        try {
            student.enrol(selectSubject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnEnroll.setDisable(true);
        loadDataIntoMyActivityTable();
    }
    @FXML
    void btnWithdraw(ActionEvent event) {
        student.withdraw(selectActivity);
        ObservableList<Activity> activities = student.getActivities();
        if(activities.size()==0){
            tbActivity.setPlaceholder(new Label("Not enrolled in any activities"));
        }
    }
    @FXML
    void selectActivityForWithdraw(MouseEvent event) {
        selectActivity = tbActivity.getSelectionModel().getSelectedItem();
        if(selectActivity !=null){
            btnEnroll.setDisable(true);
            btnWithdraw.setDisable(false);
        }
    }

    @FXML
    void selectActivity(MouseEvent event) {
        selectSubject = tbSubject.getSelectionModel().getSelectedItem();
        boolean isEnroll = student.isEnrolledIn(selectSubject);
        if(selectSubject !=null && isEnroll==false){
            btnEnroll.setDisable(false);
        }else{
            btnEnroll.setDisable(true);
        }
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
