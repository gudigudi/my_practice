package head_first_design_pattern.Adapter;

/**
 * Created by gudi on 5/17/15.
 */
public class MallardDuck implements Duck {

    public void quack() {
        System.out.println("Quack");
    }

    public void fly() {
        System.out.println("I'm flying");
    }
}
