package exercises.exercise5.observer;

public class ObserverMain {
    public static void main(String[] args) {
        System.out.println("Testing the Observer Pattern");

        WindowsSystem windowsSystem = new WindowsSystem();

        AntiVirus av1 = new AntiVirus("Avast");
        AntiVirus av2 = new AntiVirus("McAfee");

        windowsSystem.addObserver(av1);
        windowsSystem.addObserver(av2);

        windowsSystem.detectVirus("Torjan.Generic");
    }
}
