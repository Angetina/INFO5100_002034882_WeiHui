package exercises.exercise5.observer;

import exercises.exercise5.Observer;

public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String message);
}
