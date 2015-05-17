package head_first_design_pattern.TemplateMethod;

/**
 * Created by gudi on 5/17/15.
 */
public class BeverageTestDrive {

    public static void main(String[] args) {

        CoffeeWithHook coffeeWithHook = new CoffeeWithHook();

        coffeeWithHook.prepareRecipe();
    }
}
