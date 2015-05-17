package head_first_design_pattern.Command;

/**
 * Created by gudi on 5/17/15.
 */
public class SimpleRemoteControl {

    Command slot;

    public SimpleRemoteControl() {}

    public void setCommand(Command command) {
        slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();;
    }
}
