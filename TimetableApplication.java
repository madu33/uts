import au.edu.uts.ap.javafx.*;
import controller.AddNewStudentController;
import controller.UniversityController;
import model.*;
import javafx.application.*;
import javafx.stage.*;


public class TimetableApplication extends Application {



    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) throws Exception {

         ViewLoader.showStage(University.getInstance(), "/view/university.fxml", "Main menu", stage);

    }





}
