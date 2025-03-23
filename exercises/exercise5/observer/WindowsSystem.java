package exercises.exercise5.observer;

import exercises.exercise5.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class WindowsSystem implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o){
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message){
        for (Observer o : observers){
            o.update(message);
        }
    }

    public void detectVirus(String virusName){
        System.out.println("Windows system: Virus detected" + virusName);
        notifyObservers("Warning: Virus Detected" + virusName);
    }
}
