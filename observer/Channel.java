package observer;

import controller.AddNewStudentController;
import controller.UniversityController;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Observable{

    private  List<Observer>observersList;

    public Channel() {
        this.observersList = new ArrayList<>();

    }




    @Override
    public void addObserver(Observer observer) {
        observersList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observersList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer ob: observersList){
            ob.notifyMe(this);
        }
    }



    public void addNewStudent() {
        notifyObservers();
    }
}
