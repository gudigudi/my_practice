package head_first_design_pattern.Duck_class;





public class RedheadDuck extends Duck {
    public void display() {
        System.out.println("RedheadDuck has red head");
    }

    public static void main(String[] args) {
        RedheadDuck a = new RedheadDuck();
        a.quack();
        a.swim();
        a.display();
    }
}
