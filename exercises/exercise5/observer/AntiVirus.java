package exercises.exercise5.observer;

import exercises.exercise5.observer.Observer;

public class AntiVirus implements Observer {
    private String name;

    public AntiVirus(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "Antivirus software received notification:" + message);
    }
}
