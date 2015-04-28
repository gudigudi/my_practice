package head_first_design_pattern.Duck_interface2;


public class MuteQuack implements QuackBehavior {

    public void quack() {
        System.out.println("no thing!");
    }
}
