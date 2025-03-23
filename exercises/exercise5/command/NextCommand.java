package exercises.exercise5.command;

public class NextCommand implements Command {
    private BluetoothEarphone earphone;

    public NextCommand(BluetoothEarphone earphone) {
        this.earphone = earphone;
    }

    @Override
    public void execute() {
        earphone.next();
    }
}
