package exercises.exercise5.command;

public class PauseCommand implements Command {
    private BluetoothEarphone earphone;

    public PauseCommand(BluetoothEarphone earphone) {
        this.earphone = earphone;
    }

    @Override
    public void execute() {
        earphone.pause();
    }
}
