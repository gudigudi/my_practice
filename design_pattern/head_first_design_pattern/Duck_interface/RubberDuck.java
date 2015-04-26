public class RubberDuck extends Duck implements Quackable {
    public void display() {
        System.out.println("RubberDuck display!");
    }
    public void quack() {
        System.out.println("RubberDuck can quack");
    }

    public static void main(String[] args) {
        RubberDuck a = new RubberDuck();
        a.display();
        a.quack();
    }
}
