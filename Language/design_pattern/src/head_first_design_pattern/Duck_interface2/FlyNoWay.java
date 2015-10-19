package head_first_design_pattern.Duck_interface2;


public class FlyNoWay implements FlyBehavior {

    public void fly() {
        System.out.println("fly no way");
    }
}
