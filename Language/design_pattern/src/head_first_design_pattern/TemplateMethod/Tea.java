package head_first_design_pattern.TemplateMethod;

/**
 * Created by gudi on 5/17/15.
 */
public class Tea extends CaffeineBeverage {

    void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public void brew() {
        System.out.println("Steeping the tea");
    }

    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    public void addCondiments() {
        System.out.println("Adding Lemon");
    }
}
