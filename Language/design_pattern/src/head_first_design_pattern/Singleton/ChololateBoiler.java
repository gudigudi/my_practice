package head_first_design_pattern.Singleton;

/**
 * Created by gudi on 5/16/15.
 */
public class ChololateBoiler {

    private boolean empty;
    private boolean boiled;

    public ChololateBoiler() {
        empty = true;
        boiled = false;
    }

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled())
            empty = true;
    }

    public void boil() {
        if (!isBoiled() && !isBoiled())
            boiled = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
