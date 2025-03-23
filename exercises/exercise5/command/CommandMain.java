package exercises.exercise5.command;

public class CommandMain {
    public static void main(String[] args) {
        System.out.println("Testing Command Pattern");

        BluetoothEarphone earphone = new BluetoothEarphone();
        RemoteControl remote = new RemoteControl();

        remote.setCommand(new PlayCommand(earphone));
        remote.pressButton();

        remote.setCommand(new PauseCommand(earphone));
        remote.pressButton();

        remote.setCommand(new StopCommand(earphone));
        remote.pressButton();

        remote.setCommand(new NextCommand(earphone));
        remote.pressButton();
    }
}
