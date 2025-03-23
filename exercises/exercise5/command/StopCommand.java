package exercises.exercise5.command;

public class StopCommand implements Command {
    private BluetoothEarphone earphone;
    public StopCommand(BluetoothEarphone earphone) {
        this.earphone = earphone;
    }

    @Override
    public void execute() {
        earphone.stop();
    }
}
