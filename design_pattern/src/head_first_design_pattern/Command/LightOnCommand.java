package head_first_design_pattern.Command;

/**
 * Created by gudi on 5/17/15.
 */
public class LightOnCommand implements Command {

    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }
}
