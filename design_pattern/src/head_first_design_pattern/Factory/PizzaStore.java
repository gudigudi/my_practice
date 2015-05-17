package head_first_design_pattern.Factory;

/**
 * Created by gudi on 5/16/15.
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {

        Pizza pizza = null;

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
