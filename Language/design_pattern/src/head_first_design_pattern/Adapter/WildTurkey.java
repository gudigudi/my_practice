package head_first_design_pattern.Adapter;

/**
 * Created by gudi on 5/17/15.
 */
public class WildTurkey implements Turkey {

    public void gobble() {
        System.out.println("Gobble gobble");
    }

    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
