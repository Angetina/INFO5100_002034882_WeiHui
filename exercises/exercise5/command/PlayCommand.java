package exercises.exercise5.command;

public class PlayCommand implements Command {
    private BluetoothEarphone earphone;

    public PlayCommand(BluetoothEarphone earphone) {
        this.earphone = earphone;
    }

    @Override
    public void execute() {
        earphone.play();
    }
}
