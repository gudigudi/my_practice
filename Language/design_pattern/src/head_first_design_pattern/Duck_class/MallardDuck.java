package head_first_design_pattern.Duck_class;



public class MallardDuck extends Duck {
    public void display() {
        System.out.println("MallardDuck has green head");
    }

    public static void main(String[] args) {
        MallardDuck a = new MallardDuck();
        a.quack();
        a.swim();
        a.display();
    }
}
